package com.theolm.gym_share.data.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.theolm.gym_share.data.database.AppDatabase
import com.theolm.gym_share.data.database.StupidDB
import com.theolm.gym_share.data.database.StupidPref
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

    @Provides
    fun providesStupidDB(@ApplicationContext appContext: Context, moshi: Moshi): StupidDB {
        val prefs = appContext.getSharedPreferences(StupidPref, MODE_PRIVATE)
        return StupidDB(prefs, moshi)
    }

    @Provides
    fun providesMoshi(): Moshi = Moshi
        .Builder()
        .build()
}