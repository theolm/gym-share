package com.theolm.gym_share.data.repositories

import com.theolm.gym_share.data.database.WorkoutPlan
import com.theolm.gym_share.data.database.WorkoutPlanDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface WorkoutPlanRepo {
    suspend fun save(workoutPlan: WorkoutPlan)
    suspend fun getAll() : List<WorkoutPlan>
}
class WorkoutPlanRepoImpl @Inject constructor(
    private val workoutPlanDao: WorkoutPlanDao
) : WorkoutPlanRepo {
    override suspend fun save(workoutPlan: WorkoutPlan) {
        withContext(Dispatchers.IO) {
            workoutPlanDao.insertWorkoutPlan(workoutPlan)
        }
    }

    override suspend fun getAll(): List<WorkoutPlan> {
        return withContext(Dispatchers.IO) {
            workoutPlanDao.getAll()
        }
    }

}

