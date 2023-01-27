package com.theolm.core.usecase

import com.theolm.core.data.WorkoutPlan
import com.theolm.core.repository.WorkoutPlanRepo
import javax.inject.Inject

interface DeleteWorkoutPlanUseCase {
    suspend operator fun invoke(workoutPlan: WorkoutPlan)
}

object MockDeleteWorkoutPlanUseCase : DeleteWorkoutPlanUseCase {
    override suspend fun invoke(workoutPlan: WorkoutPlan) = Unit
}

internal class DeleteWorkoutPlanUseCaseImpl @Inject constructor(private val repo: WorkoutPlanRepo) :
    DeleteWorkoutPlanUseCase {
    override suspend fun invoke(workoutPlan: WorkoutPlan) {
        repo.delete(workoutPlan)
    }
}
