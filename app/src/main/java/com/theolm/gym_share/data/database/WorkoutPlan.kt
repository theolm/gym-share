package com.theolm.gym_share.data.database

import androidx.room.*


@Dao
interface WorkoutPlanDao {
    @Query("SELECT * FROM WorkoutPlan")
    fun getAll() : List<WorkoutPlan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlan(workoutPlan: WorkoutPlan)

    @Delete
    fun delete(user: WorkoutPlan)
}

@Entity
data class WorkoutPlan(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String,
)