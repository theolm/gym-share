package com.theolm.gym_share.data.repositories

import com.theolm.gym_share.data.database.WorkoutPlan
import com.theolm.gym_share.data.database.WorkoutPlanDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface WorkoutPlanRepo {
    suspend fun save(workoutPlan: WorkoutPlan)
    suspend fun delete(workoutPlan: WorkoutPlan)
    fun getAll(): Flow<List<WorkoutPlan>>
}

class WorkoutPlanRepoImpl @Inject constructor(
    private val workoutPlanDao: WorkoutPlanDao
) : WorkoutPlanRepo {
    override suspend fun save(workoutPlan: WorkoutPlan) {
        withContext(Dispatchers.IO) {
            workoutPlanDao.insertWorkoutPlan(workoutPlan)
        }
    }

    override suspend fun delete(workoutPlan: WorkoutPlan) {
        withContext(Dispatchers.IO) {
            workoutPlanDao.delete(workoutPlan)
        }
    }

    override fun getAll(): Flow<List<WorkoutPlan>> {
        return workoutPlanDao.getAll()
    }

}

class MockWorkoutPlanRepo : WorkoutPlanRepo {
    override suspend fun save(workoutPlan: WorkoutPlan) = Unit
    override suspend fun delete(workoutPlan: WorkoutPlan) = Unit
    override fun getAll(): Flow<List<WorkoutPlan>> = flow { emptyList<WorkoutPlan>() }
}

