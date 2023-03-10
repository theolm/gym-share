package com.theolm.gym_share.presentation.ui.page.addExercises

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.theolm.core.data.Exercise
import com.theolm.core.data.WorkoutPlan
import com.theolm.core.data.WorkoutSet
import com.theolm.gym_share.presentation.ui.theme.PreviewThemeDark
import com.theolm.gym_share.presentation.ui.theme.PreviewThemeLight

@Preview
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        AddExercisePage(
            navigator = EmptyDestinationsNavigator,
            resultBackNavigator = EmptyResultBackNavigator(),
            viewModel = AddExerciseViewModel.mock(),
            workoutPlan = WorkoutPlan(title = "Teste"),
            workoutSet = WorkoutSet(),
        )
    }
}

@Preview
@Composable
private fun PreviewDark() {
    PreviewThemeDark {
        AddExercisePage(
            navigator = EmptyDestinationsNavigator,
            resultBackNavigator = EmptyResultBackNavigator(),
            viewModel = AddExerciseViewModel.mock(),
            workoutPlan = WorkoutPlan(title = "Teste"),
            workoutSet = WorkoutSet(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AddExercisePage(
    navigator: DestinationsNavigator,
    resultBackNavigator: ResultBackNavigator<WorkoutPlan>,
    viewModel: AddExerciseViewModel = hiltViewModel(),
    workoutPlan: WorkoutPlan,
    workoutSet: WorkoutSet,
) {
    var title by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(text = "Testing")
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
                    val exercise = Exercise(title = title)
                    val updatedWorkout = workoutPlan.addExercise(workoutSet.id, exercise)
                    resultBackNavigator.navigateBack(updatedWorkout)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = {
//            SnackbarHost(viewModel.errorHandler.snackBarHostState)
        }
    ) { _ ->
        val focusManager = LocalFocusManager.current
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            label = { Text(text = "Title") },
            onValueChange = { title = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
    }
}