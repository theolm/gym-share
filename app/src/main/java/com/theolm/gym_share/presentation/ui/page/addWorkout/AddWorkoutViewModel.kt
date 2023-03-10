package com.theolm.gym_share.presentation.ui.page.addWorkout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.theolm.core.data.WorkoutPlan
import com.theolm.core.usecase.MockSaveWorkoutPlanUseCase
import com.theolm.core.usecase.SaveWorkoutPlanUseCase
import com.theolm.gym_share.R
import com.theolm.gym_share.presentation.ui.common.ErrorHandler
import com.theolm.gym_share.presentation.ui.common.MockErrorHandler
import com.theolm.gym_share.presentation.ui.page.destinations.AddWorkoutPageDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val saveWorkoutPlanUseCase: SaveWorkoutPlanUseCase,
    val errorHandler: ErrorHandler,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState by mutableStateOf(WorkoutUiState())

    init {
        val editWorkout = AddWorkoutPageDestination.argsFrom(savedStateHandle)
        editWorkout.workoutPlan?.let {
            updateUiState(it)
        }
    }

    fun onTitleChange(title: String) {
        uiState = uiState.copy(title = title)
    }

    fun onAddNewSet() {
        uiState = uiState.addSet()
    }

    fun onDeleteSet(position: Int) {
        uiState = uiState.deleteSet(position)
    }

    fun onEditSet(position: Int, text: String) {
        uiState = uiState.editSet(position, text)
    }

    suspend fun saveWorkoutPlan(): Boolean {
        return if (hasInputErrors()) {
            false
        } else {
            saveWorkoutPlanUseCase(uiState.toWorkoutPlan())
            true
        }
    }

    fun updateUiState(workout: WorkoutPlan) {
        uiState = WorkoutUiState.fromWorkoutPlan(workout)
    }

    /**
     * Checks for input errors and set the proper state in the ErrorHandler
     */
    private fun hasInputErrors(): Boolean {
        if (uiState.title.isBlank()) {
            errorHandler.onError(R.string.error_title_missing)
            return true
        }

        if (uiState.setList.isEmpty()) {
            errorHandler.onError(R.string.error_workout_set_missing)
            return true
        }

        uiState.setList.forEach {
            if (it.title.isBlank()) {
                errorHandler.onError(R.string.error_set_title_missing)
                return true
            }
        }

        return false
    }

    companion object {
        fun mock() = AddWorkoutViewModel(
            MockSaveWorkoutPlanUseCase,
            MockErrorHandler,
            SavedStateHandle()
        )
    }
}
