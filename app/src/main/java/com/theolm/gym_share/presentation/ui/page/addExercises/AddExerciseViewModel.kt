package com.theolm.gym_share.presentation.ui.page.addExercises

import androidx.lifecycle.ViewModel
import com.theolm.gym_share.presentation.ui.common.ErrorHandler
import com.theolm.gym_share.presentation.ui.common.MockErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    val errorHandler: ErrorHandler,
)  : ViewModel() {

    companion object {
        fun mock() = AddExerciseViewModel(
            MockErrorHandler
        )
    }
}