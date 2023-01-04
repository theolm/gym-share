package com.theolm.gym_share.ui.page.addWorkout

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.theolm.gym_share.ui.theme.PreviewThemeDark
import com.theolm.gym_share.ui.theme.PreviewThemeLight

@Preview
@Composable
private fun PreviewLight() {
    PreviewThemeLight {
        AddWorkoutPage()
    }
}

@Preview
@Composable
private fun PreviewDark() {
    PreviewThemeDark {
        AddWorkoutPage()
    }
}

@Composable
fun AddWorkoutPage() {

}