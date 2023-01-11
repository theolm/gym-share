package com.theolm.gym_share.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface WorkoutPlanDao {
    @Transaction
    @Query("SELECT * FROM DbWorkout")
    fun getAll(): Flow<List<DBWorkoutPlan>>

    @Transaction
    @Query("SELECT * FROM DbWorkout WHERE workoutId=:uid ")
    suspend fun getById(uid: Int): DBWorkoutPlan

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutPlan(workout: DbWorkout)

    @Delete
    suspend fun delete(workoutPlan: DbWorkout)
}


data class DBWorkoutPlan(
    @Embedded val workout: DbWorkout,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "workoutParentId"
    )
    val setList: List<DBWorkoutSet>

)

@Entity
data class DbWorkout(
    @PrimaryKey(autoGenerate = true) val workoutId: Int = 0,
    @ColumnInfo(name = "title") val title: String,
)

@Entity
data class DBWorkoutSet(
    @PrimaryKey(autoGenerate = true) val setId: Int = 0,
    val name: String,
    val workoutParentId: Int,
)