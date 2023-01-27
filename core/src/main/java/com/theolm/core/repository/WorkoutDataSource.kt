package com.theolm.core.repository

import com.theolm.core.data.WorkoutPlan
import kotlinx.coroutines.flow.Flow

interface WorkoutDataSource {
    suspend fun getWorkoutById(id: Int) : WorkoutPlan?
    suspend fun saveWorkout(workoutPlan: WorkoutPlan)
    suspend fun deleteWorkout(workoutPlan: WorkoutPlan)
    fun getWorkoutFlow() : Flow<List<WorkoutPlan>>
}