package com.theolm.gym_share.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.theolm.gym_share.framework.database.dao.WorkoutPlanDao
import com.theolm.gym_share.framework.database.entities.WorkoutEntity
import com.theolm.gym_share.framework.database.entities.WorkoutSetEntity

@Database(entities = [WorkoutSetEntity::class, WorkoutEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutPlanDao(): WorkoutPlanDao
}