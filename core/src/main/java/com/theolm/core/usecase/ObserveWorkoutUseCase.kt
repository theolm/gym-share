package com.theolm.core.usecase

import com.theolm.core.data.WorkoutPlan
import com.theolm.core.repository.WorkoutPlanRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ObserveWorkoutUseCase {
    operator fun invoke() : Flow<List<WorkoutPlan>>
}

object MockObserveWorkoutUseCase : ObserveWorkoutUseCase {
    override fun invoke(): Flow<List<WorkoutPlan>> = flow {  }
}

internal class ObserveWorkoutUseCaseImpl @Inject constructor(private val repo: WorkoutPlanRepo) : ObserveWorkoutUseCase {
    override fun invoke(): Flow<List<WorkoutPlan>> = repo.getAll()
}