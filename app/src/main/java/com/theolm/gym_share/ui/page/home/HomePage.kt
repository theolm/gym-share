package com.theolm.gym_share.ui.page.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    onAddClick : () -> Unit = {}
) {
    //TODO: fix topbar animation
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DefTopBar(
                title = "Gym Share",
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = paddingValues.calculateTopPadding())
        ) {
            items(100) {
                Text(text = "item $it")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}