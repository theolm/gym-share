package com.theolm.gym_share.framework.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val workoutId: Int = 0,
    @ColumnInfo(name = "title") val title: String,
)
