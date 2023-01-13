package com.theolm.gym_share.domain

import com.squareup.moshi.JsonClass
import kotlin.random.Random

@JsonClass(generateAdapter = true)
data class WorkoutPlan constructor(
    val id: Int = 0,
    val title: String,
    val setList: List<WorkoutSet> = listOf()
)

@JsonClass(generateAdapter = true)
data class WorkoutSet(
    val id: Int = Random.nextInt(),
    val title: String = "",
    val exerciseList: List<Exercise> = listOf()
)

@JsonClass(generateAdapter = true)
data class Exercise(
    val id: Int = Random.nextInt(),
    val title: String = "",
)

