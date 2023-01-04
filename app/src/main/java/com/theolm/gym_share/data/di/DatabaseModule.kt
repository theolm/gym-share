package com.theolm.gym_share.data.di

import android.content.Context
import androidx.room.Room
import com.theolm.gym_share.data.database.AppDatabase
import com.theolm.gym_share.data.database.WorkoutPlanDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideWorkoutPlanDao(appDatabase: AppDatabase): WorkoutPlanDao {
        return appDatabase.workoutPlanDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "gymShare"
        ).build()
    }
}