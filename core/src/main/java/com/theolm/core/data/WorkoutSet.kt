package com.theolm.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

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