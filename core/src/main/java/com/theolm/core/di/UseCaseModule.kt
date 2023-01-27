package com.theolm.core.di

import com.theolm.core.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
internal abstract class UseCaseModule {
    @Binds
    internal abstract fun bindsSaveWorkoutPlan(useCase: SaveWorkoutPlanUseCaseImpl): SaveWorkoutPlanUseCase

    @Binds
    internal abstract fun bindsDeleteWorkoutPlan(useCase: DeleteWorkoutPlanUseCaseImpl): DeleteWorkoutPlanUseCase

    @Binds
    internal abstract fun bindsObserveWorkoutPlan(useCase: ObserveWorkoutUseCaseImpl): ObserveWorkoutUseCase
}
