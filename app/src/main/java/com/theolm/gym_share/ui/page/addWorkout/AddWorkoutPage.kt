@file:OptIn(ExperimentalMaterial3Api::class)

package com.theolm.gym_share.ui.page.addWorkout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun AddWorkoutPage(onBackPress: () -> Unit) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DefTopBar(
                title = "Creating Workout",
                scrollBehavior = scrollBehavior,
                onBackPress = onBackPress
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 24.dp,
                vertical = paddingValues.calculateTopPadding()
            )
        ) {

        }
    }
}