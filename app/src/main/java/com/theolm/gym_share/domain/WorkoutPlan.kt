package com.theolm.gym_share.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WorkoutPlan constructor(
    val id: Int = 0,
    val title: String,
    val setList: List<WorkoutSet> = listOf()
)

@JsonClass(generateAdapter = true)
data class WorkoutSet(
    val id: Int,
    val title: String,
    val exerciseList: List<Exercise>
)

@JsonClass(generateAdapter = true)
data class Exercise(
    val id: String,
    val title: String,
)

