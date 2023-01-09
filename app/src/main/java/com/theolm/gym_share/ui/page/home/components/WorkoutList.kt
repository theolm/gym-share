@file:OptIn(ExperimentalMaterialApi::class)

package com.theolm.gym_share.ui.page.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.theolm.gym_share.data.database.WorkoutPlan

@Composable
fun WorkoutList(
    list: List<WorkoutPlan>,
    paddingValues: PaddingValues,
    onClick: (Int) -> Unit = {},
    onLongClick: (Int) -> Unit = {},
) {
    val topPadding = paddingValues.calculateTopPadding()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = topPadding + 32.dp,
            bottom = 32.dp
        )
    ) {
        items(list.size) { pos ->
            val workout = list[pos]

            WorkoutCard(
                title = workout.title,
                onClick = { onClick(pos) },
                onLongClick = { onLongClick(pos) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}