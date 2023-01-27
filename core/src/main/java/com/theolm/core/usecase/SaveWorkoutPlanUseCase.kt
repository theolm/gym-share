package com.theolm.core.usecase

import com.theolm.core.data.WorkoutPlan
import com.theolm.core.repository.WorkoutPlanRepo
import javax.inject.Inject

interface SaveWorkoutPlanUseCase {
    suspend operator fun invoke(workoutPlan: WorkoutPlan)
}

object MockSaveWorkoutPlanUseCase : SaveWorkoutPlanUseCase {
    override suspend fun invoke(workoutPlan: WorkoutPlan) = Unit
}

internal class SaveWorkoutPlanUseCaseImpl @Inject constructor(private val repo: WorkoutPlanRepo) :
    SaveWorkoutPlanUseCase {
    override suspend operator fun invoke(workoutPlan: WorkoutPlan) {
        repo.save(workoutPlan)
    }
}