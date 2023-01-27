package com.theolm.core.di

import com.theolm.core.repository.WorkoutPlanRepo
import com.theolm.core.repository.WorkoutPlanRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepoModule {
    @Singleton
    @Binds
    internal abstract fun bindsWorkoutPlanRepo(repoImpl: WorkoutPlanRepoImpl): WorkoutPlanRepo
}