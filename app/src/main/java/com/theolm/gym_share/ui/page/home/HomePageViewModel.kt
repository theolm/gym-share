@file:OptIn(ExperimentalMaterialApi::class)

package com.theolm.gym_share.ui.page.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theolm.gym_share.data.database.WorkoutPlan
import com.theolm.gym_share.data.repositories.WorkoutPlanRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val workoutPlanRepo: WorkoutPlanRepo
) : ViewModel() {
    val modalBottomSheetState = ModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var workoutList by mutableStateOf(listOf<WorkoutPlan>())
    var selectedWorkout: WorkoutPlan? = null

    init {
        loadWorkoutList()
    }

    private fun loadWorkoutList() {
        viewModelScope.launch(Dispatchers.Main) {
            workoutPlanRepo.getAll().collect {
                workoutList = it
            }
        }
    }

    suspend fun onLongClickWorkout(index: Int) {
        selectedWorkout = workoutList[index]
        modalBottomSheetState.show()
    }

    suspend fun onDeleteWorkout() {
        selectedWorkout?.let { workoutPlanRepo.delete(it) }
        selectedWorkout = null
        modalBottomSheetState.hide()
    }

    suspend fun onEditWorkout(): Int? {
        val uid = selectedWorkout?.uid ?: return null
        selectedWorkout = null
        modalBottomSheetState.hide()
        return uid
    }
}