package com.theolm.gym_share.domain

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@JsonClass(generateAdapter = true)
@Parcelize
data class WorkoutPlan constructor(
    val id: Int = 0,
    val title: String,
    val setList: List<WorkoutSet> = listOf()
) : Parcelable {
    fun addExercise(setId: Int, exercise: Exercise): WorkoutPlan {
        val set = setList.first { it.id == setId }
        val index = setList.toMutableList().indexOf(set)
        val mutableList = setList.toMutableList().apply {
            removeAt(index)
            add(index, set.addExercise(exercise))
        }
        return this.copy(setList = mutableList)
    }
}

@JsonClass(generateAdapter = true)
@Parcelize
data class WorkoutSet(
    val id: Int = Random.nextInt(),
    val title: String = "",
    val exerciseList: List<Exercise> = listOf()
) : Parcelable {
    fun addExercise(exercise: Exercise): WorkoutSet =
        copy(
            exerciseList = exerciseList.toMutableList().apply { add(exercise) }
        )
}

@JsonClass(generateAdapter = true)
@Parcelize
data class Exercise(
    val id: Int = Random.nextInt(),
    val title: String = "",
) : Parcelable

