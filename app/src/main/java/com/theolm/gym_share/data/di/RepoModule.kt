package com.theolm.gym_share.data.di

import com.theolm.gym_share.data.repositories.WorkoutPlanRepo
import com.theolm.gym_share.data.repositories.WorkoutPlanRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class RepoModule {
    @Binds
    abstract fun bindsWorkoutPlanRepo(repoImpl: WorkoutPlanRepoImpl): WorkoutPlanRepo
}