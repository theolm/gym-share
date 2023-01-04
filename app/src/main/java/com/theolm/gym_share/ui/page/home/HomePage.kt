package com.theolm.gym_share.ui.page.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theolm.gym_share.R
import com.theolm.gym_share.data.database.WorkoutPlan
import com.theolm.gym_share.ui.components.DefTopBar
import com.theolm.gym_share.ui.components.DefTopBarAction
import com.theolm.gym_share.ui.theme.PreviewThemeDark
import com.theolm.gym_share.ui.theme.PreviewThemeLight

@Preview
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        HomePage()
    }
}

@Preview
@Composable
private fun PreviewDark() {
    PreviewThemeDark {
        HomePage()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    viewModel: HomePageViewModel = hiltViewModel(),
    onAddClick: () -> Unit = {}
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

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
        if (viewModel.workoutList.isEmpty()) {
            NoWorkoutYet()
        } else {
            WorkoutList(
                list = viewModel.workoutList,
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
private fun NoWorkoutYet() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "No workout yet",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun WorkoutList(list: List<WorkoutPlan>, paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = paddingValues.calculateTopPadding()
        )
    ) {
        items(list.size) { pos ->
            val workout = list[pos]
            Text(text = "item ${workout.title}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}