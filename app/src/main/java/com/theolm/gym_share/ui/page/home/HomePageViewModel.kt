package com.theolm.gym_share.ui.page.home

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
    var workoutList by mutableStateOf(listOf<WorkoutPlan>())

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

}