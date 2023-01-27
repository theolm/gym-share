package com.theolm.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Exercise(
    val id: Int = Random.nextInt(),
    val title: String = "",
) : Parcelable
