package com.theolm.gym_share.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface WorkoutPlanDao {
    @Query("SELECT * FROM WorkoutPlan")
    fun getAll(): Flow<List<WorkoutPlan>>

    @Query("SELECT * FROM WorkoutPlan WHERE uid=:uid ")
    suspend fun getById(uid: Int): WorkoutPlan

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutPlan(workoutPlan: WorkoutPlan)

    @Delete
    suspend fun delete(workoutPlan: WorkoutPlan)
}

@Entity
data class WorkoutPlan(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "title") val title: String,
)