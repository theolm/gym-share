package com.theolm.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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