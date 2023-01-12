package com.theolm.gym_share.data.database

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.theolm.gym_share.domain.WorkoutPlan
import javax.inject.Inject

private const val SavedWorkouts = "SavedWorkouts"
const val StupidPref = "StupidPref"
@OptIn(ExperimentalStdlibApi::class)
class StupidDB @Inject() constructor(
    private val prefs: SharedPreferences,
    private val moshi: Moshi,
) {
    fun write(data: List<WorkoutPlan>) {
        val listData = MoshiWorkoutPlanList(data)
        val adapter: JsonAdapter<MoshiWorkoutPlanList> = moshi.adapter()
        val json = adapter.toJson(listData)

        prefs.edit().apply {
            putString(SavedWorkouts, json)
            apply()
        }
    }

    fun read(): List<WorkoutPlan> {
        val json = prefs.getString(SavedWorkouts, null) ?: return listOf()

        val adapter: JsonAdapter<MoshiWorkoutPlanList> = moshi.adapter()
        val listData = adapter.fromJson(json)
        return listData?.list ?: listOf()
    }

    @JsonClass(generateAdapter = true)
    data class MoshiWorkoutPlanList(val list: List<WorkoutPlan>)
}