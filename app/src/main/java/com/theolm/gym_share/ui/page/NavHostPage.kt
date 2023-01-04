package com.theolm.gym_share.ui.page

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.theolm.gym_share.ui.common.Route
import com.theolm.gym_share.ui.common.animatedComposable
import com.theolm.gym_share.ui.page.addWorkout.AddWorkoutPage
import com.theolm.gym_share.ui.page.home.HomePage

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun NavHostPage() {
    val navController = rememberAnimatedNavController()

    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        AnimatedNavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Route.HOME
        ) {
            animatedComposable(Route.HOME) {
                HomePage(
                    onAddClick = {
                        navController.navigate(Route.ADD_WORKOUT)
                    }
                )
            }

            animatedComposable(Route.ADD_WORKOUT) {
                AddWorkoutPage()
            }
        }
    }
}