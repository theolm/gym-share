package com.theolm.gym_share.presentation.ui.page.addExercises

import androidx.lifecycle.ViewModel
import com.theolm.core.repository.WorkoutPlanRepo
import com.theolm.gym_share.presentation.ui.common.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val workoutPlanRepo: WorkoutPlanRepo,
    val errorHandler: ErrorHandler,
)  : ViewModel() {
}