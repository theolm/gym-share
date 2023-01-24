package com.theolm.gym_share.ui.page.addExercises

import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.theolm.gym_share.data.repositories.MockWorkoutPlanRepo
import com.theolm.gym_share.domain.Exercise
import com.theolm.gym_share.domain.WorkoutPlan
import com.theolm.gym_share.domain.WorkoutSet
import com.theolm.gym_share.extensions.popBackStackWithResult
import com.theolm.gym_share.ui.common.Args
import com.theolm.gym_share.ui.common.MockErrorHandler
import com.theolm.gym_share.ui.common.Route
import com.theolm.gym_share.ui.theme.PreviewThemeDark
import com.theolm.gym_share.ui.theme.PreviewThemeLight
import kotlin.random.Random

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        AddExercisePage(navController = rememberAnimatedNavController())
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
private fun PreviewDark() {
    PreviewThemeDark {
        AddExercisePage(navController = rememberAnimatedNavController())
    }
}

private fun mockViewModel() = AddExerciseViewModel(
    MockWorkoutPlanRepo(),
    MockErrorHandler(),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExercisePage(
    navController: NavController,
    viewModel: AddExerciseViewModel = hiltViewModel()
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
                    val set = WorkoutSet(title = "testando", exerciseList = listOf(exercise))
                    val workoutPlan = WorkoutPlan(title = "teste", setList = listOf(set))
                    val json = workoutPlan.toJson()

                    navController.navigate("${Route.ADD_WORKOUT}?$json") {
                        launchSingleTop = true
                        restoreState = true
                    }
//                    navController.popBackStackWithResult(Args.RESULT, json)
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