package com.theolm.gym_share.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theolm.gym_share.ui.theme.PreviewThemeDark
import com.theolm.gym_share.ui.theme.PreviewThemeLight


@Preview(showBackground = true)
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        RowIconButton(
            Icons.Filled.Save,
            "Save",
            null
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDark() {
    PreviewThemeDark {
        RowIconButton(
            Icons.Filled.Save,
            "Save",
            null
        ) {}
    }
}


@Composable
fun RowIconButton(
    imageVector: ImageVector,
    title: String,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}