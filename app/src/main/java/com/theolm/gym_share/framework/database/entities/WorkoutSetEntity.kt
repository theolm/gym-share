package com.theolm.gym_share.framework.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutSetEntity(
    @PrimaryKey(autoGenerate = true) val setId: Int = 0,
    val name: String,
    val workoutParentId: Int,
)