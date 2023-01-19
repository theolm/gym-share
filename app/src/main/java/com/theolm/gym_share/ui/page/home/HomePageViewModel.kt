package com.theolm.gym_share.ui.page.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theolm.gym_share.data.repositories.WorkoutPlanRepo
import com.theolm.gym_share.domain.WorkoutPlan
import com.theolm.gym_share.ui.common.BottomSheetData
import com.theolm.gym_share.ui.common.BottomSheetHostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val workoutPlanRepo: WorkoutPlanRepo
) : ViewModel() {
    val bottomSheetHostState = BottomSheetHostState()
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

        val result = bottomSheetHostState.showBottomSheet()
        println(result)
    }

    suspend fun onDeleteWorkout(bottomSheetData: BottomSheetData) {
        selectedWorkout?.let { workoutPlanRepo.delete(it) }
        selectedWorkout = null
        bottomSheetData.complete(null)
    }

    fun onEditWorkout(bottomSheetData: BottomSheetData): Int? {
        val uid = selectedWorkout?.id ?: return null
        selectedWorkout = null
        bottomSheetData.complete(null)
        return uid
    }
}
