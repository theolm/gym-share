package com.theolm.gym_share.presentation.ui.page.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theolm.core.data.WorkoutPlan
import com.theolm.core.usecase.DeleteWorkoutPlanUseCase
import com.theolm.core.usecase.MockDeleteWorkoutPlanUseCase
import com.theolm.core.usecase.MockObserveWorkoutUseCase
import com.theolm.core.usecase.ObserveWorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val observeWorkoutUseCase: ObserveWorkoutUseCase,
    private val deleteWorkoutPlanUseCase: DeleteWorkoutPlanUseCase,
) : ViewModel() {
    val modalBottomSheetState = ModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var workoutList = mutableStateListOf<WorkoutPlan>()
    var selectedWorkout: WorkoutPlan? = null

    init {
        loadWorkoutList()
    }

    private fun loadWorkoutList() {
        viewModelScope.launch(Dispatchers.Main) {
            observeWorkoutUseCase().collect {
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
        selectedWorkout?.let { deleteWorkoutPlanUseCase(it) }
        selectedWorkout = null
        modalBottomSheetState.hide()
    }

    suspend fun onEditWorkout(): WorkoutPlan? {
        modalBottomSheetState.hide()
        return selectedWorkout
    }

    companion object {
        fun mock() = HomePageViewModel(
            MockObserveWorkoutUseCase,
            MockDeleteWorkoutPlanUseCase
        )
    }
}
