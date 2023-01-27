package com.theolm.gym_share.framework.di

import com.theolm.core.repository.WorkoutDataSource
import com.theolm.core.repository.WorkoutPlanRepo
import com.theolm.core.repository.WorkoutPlanRepoImpl
import com.theolm.gym_share.framework.database.StupidDB
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
    abstract fun bindsWorkoutPlanRepo(repoImpl: WorkoutPlanRepoImpl): WorkoutPlanRepo

    @Singleton
    @Binds
    abstract fun bindsWorkoutDataSource (workoutDataSource : StupidDB): WorkoutDataSource

}