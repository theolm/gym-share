@file:OptIn(ExperimentalMaterialApi::class)

package com.theolm.gym_share.presentation.ui.page.home

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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.theolm.core.repository.MockWorkoutPlanRepo
import com.theolm.gym_share.R
import com.theolm.gym_share.presentation.ui.common.BottomSheetWrapper
import com.theolm.gym_share.presentation.ui.common.RowIconButton
import com.theolm.gym_share.presentation.ui.components.DefTopBar
import com.theolm.gym_share.presentation.ui.components.DefTopBarAction
import com.theolm.gym_share.presentation.ui.page.destinations.AddWorkoutPageDestination
import com.theolm.gym_share.presentation.ui.page.home.components.NoWorkoutYet
import com.theolm.gym_share.presentation.ui.page.home.components.WorkoutList
import com.theolm.gym_share.presentation.ui.theme.PreviewThemeDark
import com.theolm.gym_share.presentation.ui.theme.PreviewThemeLight
import kotlinx.coroutines.launch

@Preview
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        HomePage(
            navigator = EmptyDestinationsNavigator,
            viewModel = HomePageViewModel(MockWorkoutPlanRepo)
        )
    }
}

@Preview
@Composable
private fun PreviewDark() {
    PreviewThemeDark {
        HomePage(
            navigator = EmptyDestinationsNavigator,
            viewModel = HomePageViewModel(MockWorkoutPlanRepo)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun HomePage(
    navigator: DestinationsNavigator,
    viewModel: HomePageViewModel = hiltViewModel(),
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
                            navigator.navigate(AddWorkoutPageDestination(workoutPlan = it))
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
                            onClick = {
                                navigator.navigate(AddWorkoutPageDestination())
                            }
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
