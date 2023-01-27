package com.theolm.gym_share.framework.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class WorkoutPlanEntity(
    @Embedded val workout: WorkoutEntity,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "workoutParentId"
    )
    val setList: List<WorkoutSetEntity>
)