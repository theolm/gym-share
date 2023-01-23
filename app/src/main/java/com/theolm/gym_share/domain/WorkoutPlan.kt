package com.theolm.gym_share.domain

import android.os.Parcelable
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@JsonClass(generateAdapter = true)
data class WorkoutPlan constructor(
    val id: Int = 0,
    val title: String,
    val setList: List<WorkoutSet> = listOf()
) {
    @OptIn(ExperimentalStdlibApi::class)
    fun toJson() : String {
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<WorkoutPlan> = moshi.adapter()

        return jsonAdapter.toJson(this)
    }

    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        fun fromJson(json: String): WorkoutPlan {
            val moshi: Moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<WorkoutPlan> = moshi.adapter()

            return adapter.fromJson(json) ?: throw Exception("Exception deserializing")
        }
    }
}

@JsonClass(generateAdapter = true)
data class WorkoutSet(
    val id: Int = Random.nextInt(),
    val title: String = "",
    val exerciseList: List<Exercise> = listOf()
)

@JsonClass(generateAdapter = true)
@Parcelize
data class Exercise(
    val id: Int = Random.nextInt(),
    val title: String = "",
) : Parcelable

