@file:OptIn(ExperimentalMaterialApi::class)

package com.theolm.gym_share.ui.page.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.theolm.gym_share.R
import com.theolm.gym_share.data.repositories.MockWorkoutPlanRepo
import com.theolm.gym_share.ui.common.BottomSheetWrapper
import com.theolm.gym_share.ui.common.RowIconButton
import com.theolm.gym_share.ui.components.DefTopBar
import com.theolm.gym_share.ui.components.DefTopBarAction
import com.theolm.gym_share.ui.page.home.components.NoWorkoutYet
import com.theolm.gym_share.ui.page.home.components.WorkoutList
import com.theolm.gym_share.ui.theme.PreviewThemeDark
import com.theolm.gym_share.ui.theme.PreviewThemeLight
import kotlinx.coroutines.launch

@Preview
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        HomePage(
            viewModel = HomePageViewModel(MockWorkoutPlanRepo())
        )
    }
}

@Preview
@Composable
private fun PreviewDark() {
    PreviewThemeDark {
        HomePage(
            viewModel = HomePageViewModel(MockWorkoutPlanRepo())
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    viewModel: HomePageViewModel = hiltViewModel(),
    onAddClick: () -> Unit = {},
    onEditClick: (Int) -> Unit = {}
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    val scope = rememberCoroutineScope()

    BottomSheetWrapper(
        bottomSheetState = viewModel.modalBottomSheetState,
        sheetContent = {
            RowIconButton(
                imageVector = Icons.Filled.Edit,
                title = stringResource(id = R.string.edit),
                onClick = {
                    scope.launch {
                        viewModel.onEditWorkout()?.let {
                            onEditClick.invoke(it)
                        }
                    }
                }
            )

            RowIconButton(
                imageVector = Icons.Filled.Delete,
                title = stringResource(id = R.string.delete),
                onClick = {
                    scope.launch {
                        viewModel.onDeleteWorkout()
                    }
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                DefTopBar(
                    title = stringResource(id = R.string.app_name),
                    scrollBehavior = scrollBehavior,
                    actions = listOf(
                        DefTopBarAction(
                            icon = Icons.Filled.Add,
                            onClick = onAddClick
                        )
                    )
                )
            }
        ) { paddingValues ->
            val list = viewModel.workoutList
            if (list.isEmpty()) {
                NoWorkoutYet()
            } else {
                WorkoutList(
                    list = list,
                    paddingValues = paddingValues,
                    onLongClick = {
                        scope.launch {
                            viewModel.onLongClickWorkout(it)
                        }
                    }
                )
            }
        }
    }
}

