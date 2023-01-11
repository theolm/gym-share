package com.theolm.gym_share.domain

data class WorkoutPlan constructor(
    val id: Int = 0,
    val title: String,
    val setList: List<WorkoutSet> = listOf()
)

data class WorkoutSet(
    val id: Int,
    val title: String,
    val exerciseList: List<Exercise>
)

data class Exercise(
    val id: String,
    val title: String,
)

