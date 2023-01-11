package com.theolm.gym_share.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbWorkout::class, DBWorkoutSet::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutPlanDao(): WorkoutPlanDao
}