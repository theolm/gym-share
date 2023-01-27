package com.theolm.gym_share.framework.database

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.theolm.core.data.Exercise
import com.theolm.core.data.WorkoutPlan
import com.theolm.core.data.WorkoutSet
import com.theolm.core.repository.WorkoutDataSource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import kotlin.random.Random

private const val SavedWorkouts = "SavedWorkouts"
const val StupidPref = "StupidPref"

@OptIn(ExperimentalStdlibApi::class)
class StupidDB @Inject() constructor(
    private val prefs: SharedPreferences,
    private val moshi: Moshi,
) : WorkoutDataSource {
    private val list = ArrayList(read())
    private val sharedFlow =
        MutableSharedFlow<List<WorkoutPlan>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

    init {
        sharedFlow.tryEmit(list)
    }

    private fun write(data: List<WorkoutPlan>) {
        val listData = MoshiWorkoutPlanList(data.map { SWorkoutPlan.fromData(it) })
        val adapter: JsonAdapter<MoshiWorkoutPlanList> = moshi.adapter()
        val json = adapter.toJson(listData)

        prefs.edit().apply {
            putString(SavedWorkouts, json)
            apply()
        }
    }

    private fun read(): List<WorkoutPlan> {
        val json = prefs.getString(SavedWorkouts, null) ?: return listOf()

        val adapter: JsonAdapter<MoshiWorkoutPlanList> = moshi.adapter()
        val listData = adapter.fromJson(json)
        return listData?.list?.map { it.toData() } ?: listOf()
    }

    override suspend fun getWorkoutById(id: Int): WorkoutPlan? {
        return list.find { it.id == id }
    }

    override suspend fun saveWorkout(workoutPlan: WorkoutPlan) {
        val updateIndex = list.indexOfFirst { it.id == workoutPlan.id }

        if (updateIndex >= 0) {
            /* Update */
            list.removeAt(updateIndex)
            list.add(updateIndex, workoutPlan)
        } else {
            /* Create new */
            val memWorkout = workoutPlan.copy(id = Random.nextInt())
            list.add(memWorkout)
        }

        sharedFlow.emit(ArrayList(list))
        write(list)
    }

    override suspend fun deleteWorkout(workoutPlan: WorkoutPlan) {
        list.remove(workoutPlan)
        sharedFlow.emit(ArrayList(list))
        write(list)
    }

    override fun getWorkoutFlow(): Flow<List<WorkoutPlan>> = sharedFlow
}

/**
 * Temporary data classes. This will be used only during the initial development,
 * after that I should migrate to @Entity and use room
 */
@JsonClass(generateAdapter = true)
data class MoshiWorkoutPlanList(val list: List<SWorkoutPlan>)

@JsonClass(generateAdapter = true)
data class SWorkoutPlan(
    val id: Int = 0,
    val title: String,
    val setList: List<SWorkoutSet> = listOf()
) {
    fun toData() = WorkoutPlan(
        id = this.id,
        title = this.title,
        setList = this.setList.map { it.toData() }
    )

    companion object {
        fun fromData(data: WorkoutPlan) = SWorkoutPlan(
            id = data.id,
            title = data.title,
            setList = data.setList.map { SWorkoutSet.fromData(it) }
        )
    }
}

@JsonClass(generateAdapter = true)
data class SWorkoutSet(
    val id: Int = Random.nextInt(),
    val title: String = "",
    val exerciseList: List<SExercise> = listOf()
) {
    fun toData() = WorkoutSet(
        id = this.id,
        title = this.title,
        exerciseList = this.exerciseList.map { it.toData() }
    )

    companion object {
        fun fromData(data: WorkoutSet) = SWorkoutSet(
            id = data.id,
            title = data.title,
            exerciseList = data.exerciseList.map { SExercise.fromData(it) }
        )
    }
}

@JsonClass(generateAdapter = true)
data class SExercise(
    val id: Int = Random.nextInt(),
    val title: String = ""
) {
    fun toData() = Exercise(
        id = this.id,
        title = this.title
    )

    companion object {
        fun fromData(data: Exercise) = SExercise(
            id = data.id,
            title = data.title
        )
    }
}
