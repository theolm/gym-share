package com.theolm.gym_share.ui.page.addWorkout

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theolm.gym_share.data.database.WorkoutPlan
import com.theolm.gym_share.data.repositories.WorkoutPlanRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val workoutPlanRepo: WorkoutPlanRepo
) : ViewModel() {
    val snackBarHostState = SnackbarHostState()
    var titleState by mutableStateOf("")
    val setList = mutableStateListOf<MutableState<String>>()

    fun onAddNewSet() {
        setList.add(mutableStateOf(""))
    }

    fun onDeleteSet(position: Int) {
        setList.removeAt(position)
    }

    suspend fun saveWorkoutPlan(): Boolean {
        if (titleState.isBlank()) {
            showErrorMessage("Please add a title")
            return false
        }

        setList.forEach {
            if (it.value.isBlank()) {
                showErrorMessage("Please add a title to every set")
                return false
            }
        }

        workoutPlanRepo.save(WorkoutPlan(title = titleState))
        return true
    }

    private fun showErrorMessage(message: String) {
        viewModelScope.launch {
            snackBarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short,
                withDismissAction = false
            )
        }
    }
}