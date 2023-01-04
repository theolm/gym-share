package com.theolm.gym_share.ui.page.addWorkout

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.theolm.gym_share.data.database.WorkoutPlan
import com.theolm.gym_share.data.repositories.WorkoutPlanRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val workoutPlanRepo: WorkoutPlanRepo
) : ViewModel() {
    var titleState by mutableStateOf("")

    val setList = mutableStateListOf<MutableState<String>>()

    fun onAddNewSet() {
        setList.add(mutableStateOf(""))
    }

    fun onDeleteSet(position: Int) {
        setList.removeAt(position)
    }

    suspend fun saveWorkoutPlan() {
        workoutPlanRepo.save(WorkoutPlan(title = titleState))
    }
}