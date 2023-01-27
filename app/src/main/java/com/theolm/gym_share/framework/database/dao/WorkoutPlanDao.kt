package com.theolm.gym_share.framework.database.dao

import androidx.room.*
import com.theolm.gym_share.framework.database.entities.WorkoutEntity
import com.theolm.gym_share.framework.database.entities.WorkoutPlanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutPlanDao {
    @Transaction
    @Query("SELECT * FROM WorkoutEntity")
    fun getAll(): Flow<List<WorkoutPlanEntity>>

    @Transaction
    @Query("SELECT * FROM WorkoutEntity WHERE workoutId=:uid ")
    suspend fun getById(uid: Int): WorkoutPlanEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutPlan(workout: WorkoutEntity)

    @Delete
    suspend fun delete(workoutPlan: WorkoutEntity)
}
