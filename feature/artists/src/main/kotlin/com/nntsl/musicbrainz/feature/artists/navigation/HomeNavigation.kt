package com.nntsl.musicbrainz.feature.artists.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nntsl.musicbrainz.feature.artists.HomeRoute

const val homeRoute = "home_route"
const val homeRoutePattern = "home_graph"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoutePattern, navOptions)
}

fun NavGraphBuilder.homeGraph(
    navigateToArtist: (String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = homeRoutePattern,
        startDestination = homeRoute
    ) {
        composable(route = homeRoute) {
            HomeRoute(
                navigateToArtist = navigateToArtist
            )
        }
        nestedGraphs()
    }
}
