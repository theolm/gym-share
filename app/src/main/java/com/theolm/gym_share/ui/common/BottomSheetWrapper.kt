@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class
)

package com.theolm.gym_share.ui.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

private val cornerSize = CornerSize(28.dp)
private val shape = ShapeDefaults.Large.copy(
    topStart = cornerSize,
    topEnd = cornerSize,
    bottomEnd = CornerSize(0.dp),
    bottomStart = CornerSize(0.dp),
)

private val elevation = 1.dp

@Composable
fun BottomSheetWrapper(
    modifier: Modifier = Modifier,
    bottomSheetState: ModalBottomSheetState,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    BackHandler(bottomSheetState.isVisible) {
        scope.launch { bottomSheetState.hide() }
    }

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = bottomSheetState,
        sheetShape = shape,
        sheetElevation = elevation,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetContentColor = contentColorFor(MaterialTheme.colorScheme.surface),
        content = content,
        sheetContent = {
            Column(modifier = Modifier.padding(horizontal = 28.dp)) {
                Spacer(modifier = Modifier.height(44.dp))
                sheetContent.invoke(this)
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    )
}