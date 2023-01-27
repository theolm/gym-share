package com.theolm.core.repository

import com.theolm.core.data.WorkoutPlan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


internal interface WorkoutPlanRepo {
    suspend fun get(id: Int): WorkoutPlan
    suspend fun save(workoutPlan: WorkoutPlan)
    suspend fun delete(workoutPlan: WorkoutPlan)
    fun getAll(): Flow<List<WorkoutPlan>>
}

internal class WorkoutPlanRepoImpl @Inject constructor(
    private val workoutDataSource: WorkoutDataSource
) : WorkoutPlanRepo {
    override suspend fun get(id: Int): WorkoutPlan {
        return withContext(Dispatchers.IO) {
            //TODO: fix this
            workoutDataSource.getWorkoutById(id)!!
        }
    }

    override suspend fun save(workoutPlan: WorkoutPlan) {
        withContext(Dispatchers.IO) {
            workoutDataSource.saveWorkout(workoutPlan)
        }
    }

    override suspend fun delete(workoutPlan: WorkoutPlan) {
        withContext(Dispatchers.IO) {
            workoutDataSource.deleteWorkout(workoutPlan)
        }
    }

    override fun getAll(): Flow<List<WorkoutPlan>> {
        return workoutDataSource.getWorkoutFlow()
    }
}
