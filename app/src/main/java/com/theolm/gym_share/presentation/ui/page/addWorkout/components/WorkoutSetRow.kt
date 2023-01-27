package com.theolm.gym_share.presentation.ui.page.addWorkout.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theolm.core.data.Exercise
import com.theolm.core.data.WorkoutSet
import com.theolm.gym_share.presentation.ui.theme.PreviewThemeDark
import com.theolm.gym_share.presentation.ui.theme.PreviewThemeLight

@Preview
@Composable
private fun PreviewLight() {
    val set = WorkoutSet(
        title = "Mock set",
        exerciseList = listOf(
            Exercise(title = "Exercise 1"),
            Exercise(title = "Exercise 2"),
            Exercise(title = "Exercise 3"),
        )
    )
    PreviewThemeLight {
        WorkoutSetRow(
            workoutSet = set,
            setLetter = 'A',
            onValueChange = {},
            onDeleteClick = {}
        ) {

        }
    }
}

@Preview
@Composable
private fun PreviewDark() {
    val set = WorkoutSet(
        title = "Mock set",
        exerciseList = listOf(
            Exercise(title = "Exercise 1"),
            Exercise(title = "Exercise 2"),
            Exercise(title = "Exercise 3"),
        )
    )
    PreviewThemeDark {
        WorkoutSetRow(
            workoutSet = set,
            setLetter = 'A',
            onValueChange = {},
            onDeleteClick = {}
        ) {

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutSetRow(
    workoutSet: WorkoutSet,
    setLetter: Char,
    onValueChange: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onAddClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column {
        Row(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = setLetter.toString(),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp),
                value = workoutSet.title,
                onValueChange = onValueChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
            )

            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = onAddClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }

            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = onDeleteClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        workoutSet.exerciseList.forEach {
            Row(modifier = Modifier.padding(start = 24.dp)) {
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}