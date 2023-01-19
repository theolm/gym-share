package com.theolm.gym_share.ui.common

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.resume

@OptIn(ExperimentalMaterialApi::class)
@Stable
class BottomSheetHostState {
    val modalBottomSheetState = ModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            if (it == ModalBottomSheetValue.Hidden) {
                currentBsState?.dismiss()
            }
            true
        }
    )
    private val mutex = Mutex()

    var currentBsState by mutableStateOf<BottomSheetData?>(null)
        private set


    suspend fun showBottomSheet(): BottomSheetResult = mutex.withLock {
        try {
            modalBottomSheetState.show()
            return suspendCancellableCoroutine { continuation ->
                currentBsState = BottomSheetDataImpl(continuation)
            }
        } finally {
            currentBsState = null
            modalBottomSheetState.hide()
        }


    }

    fun dismiss() {
        currentBsState?.dismiss()
        currentBsState = null
    }
}

interface BottomSheetData {
    //TODO: replace result for typesafe solution
    fun complete(result: Any?)
    fun dismiss()
}

@Stable
private class BottomSheetDataImpl(
    val cancelableContinuation: CancellableContinuation<BottomSheetResult>
) : BottomSheetData {
    override fun complete(result: Any?) {
        cancelableContinuation.takeIf { it.isActive }?.resume(BottomSheetResult(result))
    }

    override fun dismiss() {
        cancelableContinuation.takeIf { it.isActive }?.resume(BottomSheetResult())
    }

}

data class BottomSheetResult(val result: Any? = null)