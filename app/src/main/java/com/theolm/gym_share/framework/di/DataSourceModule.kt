package com.theolm.gym_share.framework.di

import com.theolm.core.repository.WorkoutDataSource
import com.theolm.gym_share.framework.database.StupidDB
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindsWorkoutDataSource (workoutDataSource : StupidDB): WorkoutDataSource

}
