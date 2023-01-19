package com.theolm.gym_share.ui.page.addWorkout.components

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
import com.theolm.gym_share.ui.theme.PreviewThemeDark
import com.theolm.gym_share.ui.theme.PreviewThemeLight


@Preview(showBackground = true)
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        WorkoutSetRow(
            text = "Test exercise",
            alphabetPos = 'A',
            onTextChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDark() {
    PreviewThemeDark {
        WorkoutSetRow(
            text = "Test exercise",
            alphabetPos = 'A',
            onTextChange = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutSetRow(
    text: String,
    alphabetPos: Char,
    onTextChange: (String) -> Unit,
    onAddClick : () -> Unit = {},
    onDeleteClick : () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Row(Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "$alphabetPos.",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(16.dp))
        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            value = text,
            onValueChange = onTextChange,
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
            onClick = onAddClick,
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }

        IconButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = onDeleteClick,
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null
            )
        }
    }
}