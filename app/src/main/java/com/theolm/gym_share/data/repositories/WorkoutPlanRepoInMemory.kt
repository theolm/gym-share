package com.theolm.gym_share.data.repositories

import com.theolm.gym_share.data.database.StupidDB
import com.theolm.gym_share.domain.WorkoutPlan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random


class WorkoutPlanRepoInMemory @Inject constructor(
    private val stupidDB: StupidDB
) : WorkoutPlanRepo {
    private val list = ArrayList(stupidDB.read())
    private val sharedFlow =
        MutableSharedFlow<List<WorkoutPlan>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

    init {
        sharedFlow.tryEmit(list)
    }

    override suspend fun get(id: Int): WorkoutPlan {
        return list.find { it.id == id } ?: throw Exception("WorkoutPlan don't exist")
    }

    override suspend fun save(workoutPlan: WorkoutPlan) {
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

        dbWrite()
    }

    override suspend fun delete(workoutPlan: WorkoutPlan) {
        list.remove(workoutPlan)
        sharedFlow.emit(ArrayList(list))

        dbWrite()
    }

    override fun getAll(): Flow<List<WorkoutPlan>> = sharedFlow

    private suspend fun dbWrite() {
        withContext(Dispatchers.IO) { stupidDB.write(list) }
    }

}
