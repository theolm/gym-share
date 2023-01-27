package com.theolm.gym_share.presentation.ui.page.addWorkout

import com.theolm.core.data.WorkoutPlan
import com.theolm.core.data.WorkoutSet

data class WorkoutUiState(
    val uid: Int? = null,
    val title: String = "",
    val setList: List<WorkoutSet> = listOf(),
) {
    fun addSet() = copy(
        setList = setList.toMutableList().apply { add(WorkoutSet()) }
    )

    fun deleteSet(index: Int) = copy(
        setList = setList.toMutableList().apply { removeAt(index) }
    )

    fun editSet(index: Int, title: String) =
        copy(
            setList = setList.toMutableList().apply {
                removeAt(index)
                add(index, WorkoutSet(title = title))
            }
        )

    fun toWorkoutPlan() =
        WorkoutPlan(
            id = uid ?: 0,
            title = title,
            setList = setList
        )

    companion object {
        fun fromWorkoutPlan(workoutPlan: WorkoutPlan) = WorkoutUiState(
            uid = workoutPlan.id,
            title = workoutPlan.title,
            setList = workoutPlan.setList
        )
    }
}
