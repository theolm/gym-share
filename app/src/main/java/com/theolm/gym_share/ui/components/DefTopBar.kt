@file:OptIn(ExperimentalMaterial3Api::class)

package com.theolm.gym_share.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.theolm.gym_share.ui.theme.PreviewThemeDark
import com.theolm.gym_share.ui.theme.PreviewThemeLight

@Preview
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        DefTopBar(
            title = "Gym Share",
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        )
    }
}

@Preview
@Composable
private fun PreviewDark() {
    TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    PreviewThemeDark {
        DefTopBar(
            title = "Gym Share",
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        )
    }
}

@Composable
fun DefTopBar(
    title: String,
    actions: List<DefTopBarAction> = listOf(),
    scrollBehavior : TopAppBarScrollBehavior
) {
    LargeTopAppBar(
        modifier = Modifier,
        title = {
            Text(text = title)
        },
        scrollBehavior = scrollBehavior,
        actions = {
            actions.forEach { icon ->
                IconButton(
                    onClick = icon.onClick,
                ) {
                    androidx.compose.material.Icon(
                        imageVector = icon.icon,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        contentDescription = icon.contentDescription
                    )
                }
            }
        }
    )
}

data class DefTopBarAction(
    val icon: ImageVector,
    val onClick : () -> Unit,
    val contentDescription: String? = null
)