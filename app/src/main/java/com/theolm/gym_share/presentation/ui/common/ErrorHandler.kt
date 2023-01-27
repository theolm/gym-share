package com.theolm.gym_share.presentation.ui.common

import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import javax.inject.Inject

interface ErrorHandler {
    val snackBarHostState: SnackbarHostState
    fun onError(@StringRes errorRes: Int)
    suspend fun showErrorMessage(message: String)

    @Composable
    fun ErrorObserver()
}

class MockErrorHandler : ErrorHandler {
    override val snackBarHostState: SnackbarHostState
        get() = SnackbarHostState()

    override fun onError(errorRes: Int) {}

    override suspend fun showErrorMessage(message: String) {}

    @Composable
    override fun ErrorObserver() {}

}

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    override val snackBarHostState = SnackbarHostState()
    private var errorState by mutableStateOf<Int?>(null)

    override fun onError(@StringRes errorRes: Int) {
        errorState = errorRes
    }

    override suspend fun showErrorMessage(message: String) {
        snackBarHostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short,
            withDismissAction = true
        )

        errorState = null
    }

    @Composable
    override fun ErrorObserver() {
        val context = LocalContext.current
        LaunchedEffect(errorState) {
            errorState?.let {
                showErrorMessage(context.getString(it))
            }

        }
    }
}
