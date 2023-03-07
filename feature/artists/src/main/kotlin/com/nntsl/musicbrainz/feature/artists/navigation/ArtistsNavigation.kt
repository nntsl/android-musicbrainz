package com.nntsl.musicbrainz.feature.artists.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

const val artistsRoute = "artists_route"
const val artistsRoutePattern = "artists_graph"

fun NavGraphBuilder.artistsGraph(
    isExpandedScreen: Boolean
) {
    navigation(
        route = artistsRoutePattern,
        startDestination = artistsRoute
    ) {
        composable(route = artistsRoute) {
            ArtistsRoute(
                isExpandedScreen = isExpandedScreen,
            )
        }
    }
}
