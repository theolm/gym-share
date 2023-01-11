package com.theolm.gym_share.data.di

import com.theolm.gym_share.data.repositories.WorkoutPlanRepo
import com.theolm.gym_share.data.repositories.WorkoutPlanRepoInMemory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {

    @Singleton
    @Binds
    abstract fun bindsWorkoutPlanRepo(repoImpl: WorkoutPlanRepoInMemory): WorkoutPlanRepo
}