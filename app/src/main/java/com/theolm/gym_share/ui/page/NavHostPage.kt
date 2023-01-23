package com.theolm.gym_share.ui.page

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.theolm.gym_share.ui.common.Args
import com.theolm.gym_share.ui.common.Route
import com.theolm.gym_share.ui.common.animatedComposable
import com.theolm.gym_share.ui.page.addExercises.AddExercisePage
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
                    },
                    onEditClick = {
                        val json = it.toJson()
                        navController.navigate("${Route.ADD_WORKOUT}?$json")
                    }
                )
            }

            /**
             * This route is responsible for add and edit the workout plan.
             * If the route contains the Args.WORKOUT param it's an edit
             * otherwise it's adding a new workout.
             */
            animatedComposable(
                "${Route.ADD_WORKOUT}?{${Args.WORKOUT}}",
                arguments = listOf(
                    navArgument(Args.WORKOUT) {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) {
                AddWorkoutPage(navController = navController)
            }

            animatedComposable(route = Route.ADD_EXERCISE) {
                AddExercisePage(navController = navController)
            }
        }
    }
}