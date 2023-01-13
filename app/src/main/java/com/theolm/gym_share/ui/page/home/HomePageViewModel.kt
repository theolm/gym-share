package com.theolm.gym_share.ui.page.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theolm.gym_share.data.repositories.WorkoutPlanRepo
import com.theolm.gym_share.domain.WorkoutPlan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val workoutPlanRepo: WorkoutPlanRepo
) : ViewModel() {
    val modalBottomSheetState = ModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var workoutList = mutableStateListOf<WorkoutPlan>()
    var selectedWorkout: WorkoutPlan? = null

    init {
        loadWorkoutList()
    }

    private fun loadWorkoutList() {
        viewModelScope.launch(Dispatchers.Main) {
            workoutPlanRepo.getAll().collect {
                workoutList.clear()
                workoutList.addAll(it)
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
        val uid = selectedWorkout?.id ?: return null
        selectedWorkout = null
        modalBottomSheetState.hide()
        return uid
    }
}
