@file:OptIn(ExperimentalMaterial3Api::class)

package com.theolm.gym_share.ui.page.addWorkout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theolm.gym_share.R
import com.theolm.gym_share.extensions.toAlphabetLetter
import com.theolm.gym_share.ui.components.DefTopBar
import com.theolm.gym_share.ui.theme.PreviewThemeDark
import com.theolm.gym_share.ui.theme.PreviewThemeLight

@Preview
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        AddWorkoutPage {}
    }
}

@Preview
@Composable
private fun PreviewDark() {
    PreviewThemeDark {
        AddWorkoutPage {}
    }
}

@Composable
fun AddWorkoutPage(
    viewModel: AddWorkoutViewModel = hiltViewModel(),
    onBackPress: () -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DefTopBar(
                title = stringResource(id = R.string.add_workout_plan_page_title),
                scrollBehavior = scrollBehavior,
                onBackPress = onBackPress,
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
                onClick = { /*TODO*/ })
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = paddingValues.calculateTopPadding()
            )
        ) {
            item {
                val focusManager = LocalFocusManager.current
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.titleState,
                    label = {
                        Text(text = stringResource(id = R.string.workout_title))
                    },
                    onValueChange = {
                        viewModel.titleState = it
                    },
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

            items(viewModel.setList.size) { pos ->
                WorkoutSetRow(viewModel, pos)
            }
        }
    }
}

@Composable
private fun WorkoutSetRow(viewModel: AddWorkoutViewModel, pos: Int) {
    var state by viewModel.setList[pos]
    val focusManager = LocalFocusManager.current

    Row(Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "${pos.toAlphabetLetter()}.",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(16.dp))
        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            value = state,
            onValueChange = { state = it },
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
            onClick = {
                viewModel.onDeleteSet(pos)
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null
            )
        }
    }
}

