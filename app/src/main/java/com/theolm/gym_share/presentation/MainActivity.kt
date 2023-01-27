package com.theolm.gym_share.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.ramcosta.composedestinations.DestinationsNavHost
import com.theolm.gym_share.presentation.ui.page.NavGraphs
import com.theolm.gym_share.presentation.ui.theme.GymShareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
            v.setPadding(0, 0, 0, 0)
            insets
        }

        setContent {
            GymShareTheme {
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}
