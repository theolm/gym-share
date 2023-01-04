package com.theolm.gym_share.ui.page.addWorkout

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddWorkoutViewModel @Inject constructor() : ViewModel() {
    var titleState by mutableStateOf("")

    val setList = mutableStateListOf<MutableState<String>>()

    fun onAddNewSet() {
        setList.add(mutableStateOf(""))
    }

    fun onDeleteSet(position: Int) {
        setList.removeAt(position)
    }
}