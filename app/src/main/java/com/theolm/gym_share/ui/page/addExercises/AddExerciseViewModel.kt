package com.theolm.gym_share.ui.page.addExercises

import androidx.lifecycle.ViewModel
import com.theolm.gym_share.data.repositories.WorkoutPlanRepo
import com.theolm.gym_share.ui.common.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val workoutPlanRepo: WorkoutPlanRepo,
    val errorHandler: ErrorHandler
)  : ViewModel() {
}