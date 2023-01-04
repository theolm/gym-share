package com.theolm.gym_share

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.theolm.gym_share.ui.page.NavHostPage
import com.theolm.gym_share.ui.theme.GymShareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymShareTheme {
                NavHostPage()
            }
        }
    }
}
