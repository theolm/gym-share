package com.theolm.gym_share.ui.page.addWorkout

import com.theolm.gym_share.domain.WorkoutPlan
import com.theolm.gym_share.domain.WorkoutSet

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
}
