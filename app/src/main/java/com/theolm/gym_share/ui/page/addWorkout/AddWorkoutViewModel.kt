package com.theolm.gym_share.ui.page.addWorkout

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.theolm.gym_share.R
import com.theolm.gym_share.data.database.WorkoutPlan
import com.theolm.gym_share.data.repositories.WorkoutPlanRepo
import com.theolm.gym_share.ui.common.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val workoutPlanRepo: WorkoutPlanRepo,
    val errorHandler: ErrorHandler
) : ViewModel() {
    var titleState by mutableStateOf("")
    val setList = mutableStateListOf<MutableState<String>>()

    fun onAddNewSet() {
        setList.add(mutableStateOf(""))
    }

    fun onDeleteSet(position: Int) {
        setList.removeAt(position)
    }

    suspend fun saveWorkoutPlan(): Boolean {
        return if (hasInputErrors()) {
            false
        } else {
            workoutPlanRepo.save(WorkoutPlan(title = titleState))
            true
        }
    }

    /**
     * Checks for input errors and set the proper state in the ErrorHandler
     */
    private fun hasInputErrors() : Boolean{
        if (titleState.isBlank()) {
            errorHandler.onError(R.string.error_title_missing)
            return true
        }

        setList.forEach {
            if (it.value.isBlank()) {
                errorHandler.onError(R.string.error_set_title_missing)
                return true
            }
        }

        return false
    }
}
