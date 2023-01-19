package com.theolm.gym_share.ui.common.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.animatedComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) = composable(
    route = route,
    arguments = arguments,
    deepLinks = deepLinks,
    enterTransition = {
        fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                scaleIn(
                    initialScale = 0.92f,
                    animationSpec = tween(220, delayMillis = 90)
                )
    },
    exitTransition = {
        fadeOut(animationSpec = tween(90))
    },
    popEnterTransition = {
        fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                scaleIn(
                    initialScale = 0.92f,
                    animationSpec = tween(220, delayMillis = 90)
                )
    },
    popExitTransition = {
        fadeOut(animationSpec = tween(90))
    },
    content = content
)


fun slideInVertically(
    animationSpec: FiniteAnimationSpec<IntOffset> =
        spring(
            stiffness = Spring.StiffnessMediumLow,
            visibilityThreshold = IntOffset.VisibilityThreshold
        ),
    initialOffsetY: (fullHeight: Int) -> Int = { it },
): EnterTransition =
    slideIn(
        initialOffset = { IntOffset(0, initialOffsetY(it.height)) },
        animationSpec = animationSpec
    )

fun slideOutVertically(
    animationSpec: FiniteAnimationSpec<IntOffset> =
        spring(
            stiffness = Spring.StiffnessMediumLow,
            visibilityThreshold = IntOffset.VisibilityThreshold
        ),
    targetOffsetY: (fullHeight: Int) -> Int = { it },
): ExitTransition =
    slideOut(
        targetOffset = { IntOffset(0, targetOffsetY(it.height)) },
        animationSpec = animationSpec
    )

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.slideInComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) = composable(
    route = route,
    arguments = arguments,
    deepLinks = deepLinks,
    enterTransition = { slideInVertically() },
    exitTransition = { slideOutVertically() },
    popEnterTransition = { slideInVertically() },
    popExitTransition = { slideOutVertically() },
    content = content
)
