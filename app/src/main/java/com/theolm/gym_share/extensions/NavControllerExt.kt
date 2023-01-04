package com.theolm.gym_share.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController

@Composable
fun <T> NavController.GetOnceResult(keyResult: String, onResult: (T) -> Unit) {
    val valueScreenResult = currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(keyResult)?.observeAsState()

    valueScreenResult?.value?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(keyResult)
    }
}

@Composable
fun <T> NavController.ObserveResult(keyResult: String) =
    currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(keyResult)?.observeAsState()


@Composable
fun NavController.RemoveResult(keyResult: String) {
    currentBackStackEntry
        ?.savedStateHandle
        ?.remove<Any>(keyResult)
}

fun <T> NavController.popBackStackWithResult(keyResult: String, data: T) {
    currentBackStackEntry
        ?.savedStateHandle
        ?.set<T>(keyResult, data)

    this.popBackStack()
}