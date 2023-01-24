package com.theolm.gym_share.ui.page.addWorkout

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultRecipient
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import com.theolm.gym_share.R
import com.theolm.gym_share.data.repositories.MockWorkoutPlanRepo
import com.theolm.gym_share.domain.WorkoutPlan
import com.theolm.gym_share.extensions.toAlphabetLetter
import com.theolm.gym_share.ui.common.MockErrorHandler
import com.theolm.gym_share.ui.components.DefTopBar
import com.theolm.gym_share.ui.page.addWorkout.components.WorkoutSetRow
import com.theolm.gym_share.ui.page.destinations.AddExercisePageDestination
import com.theolm.gym_share.ui.theme.PreviewThemeDark
import com.theolm.gym_share.ui.theme.PreviewThemeLight
import kotlinx.coroutines.launch

@Preview
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        AddWorkoutPage(
            navigator = EmptyDestinationsNavigator,
            viewModel = mockViewModel(),
            resultRecipient = EmptyResultRecipient(),
        )
    }
}

@Preview
@Composable
private fun PreviewDark() {
    PreviewThemeDark {
        AddWorkoutPage(
            navigator = EmptyDestinationsNavigator,
            viewModel = mockViewModel(),
            resultRecipient = EmptyResultRecipient(),
        )
    }
}

fun mockViewModel() = AddWorkoutViewModel(
    MockWorkoutPlanRepo(),
    MockErrorHandler(),
)

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AddWorkoutPage(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<AddExercisePageDestination, WorkoutPlan>,
    viewModel: AddWorkoutViewModel = hiltViewModel(),
    workoutPlan: WorkoutPlan? = null,
) {
    val scope = rememberCoroutineScope()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    LaunchedEffect(true) {
        workoutPlan?.let { viewModel.updateUiState(it) }
    }

    resultRecipient.onNavResult {
        Log.d("Test", "onNavResult")
        if (it is NavResult.Value) {
            viewModel.updateUiState(it.value)
        }
    }

    val uiState = viewModel.uiState
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DefTopBar(
                title = stringResource(id = R.string.add_workout_plan_page_title),
                scrollBehavior = scrollBehavior,
                onBackPress = {
                    navigator.popBackStack()
                },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = "Save Plan")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = null
                    )
                },
                onClick = {
                    scope.launch {
                        if (viewModel.saveWorkoutPlan()) {
                            navigator.popBackStack()
                        }
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(viewModel.errorHandler.snackBarHostState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = paddingValues.calculateTopPadding() + 16.dp
            )
        ) {
            item {
                val focusManager = LocalFocusManager.current
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.title,
                    label = { Text(text = stringResource(id = R.string.workout_title)) },
                    onValueChange = { viewModel.onTitleChange(it) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Sets",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onClick = {
                            viewModel.onAddNewSet()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(uiState.setList.size) { position ->
                val workoutSet = viewModel.uiState.setList[position]
                WorkoutSetRow(
                    workoutSet = workoutSet,
                    setLetter = position.toAlphabetLetter(),
                    onValueChange = {
                        viewModel.onEditSet(position, it)
                    },
                    onDeleteClick = {
                        viewModel.onDeleteSet(position)
                    },
                    onAddClick = {
                        navigator.navigate(
                            direction = AddExercisePageDestination(
                                workoutPlan = viewModel.uiState.toWorkoutPlan(),
                                workoutSet = workoutSet
                            ),
                        )
                    }
                )
            }
        }
    }

    viewModel.errorHandler.ErrorObserver()
}
