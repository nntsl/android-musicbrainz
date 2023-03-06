package com.nntsl.musicbrainz.feature.artists.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nntsl.musicbrainz.feature.artists.ArtistsRoute

const val artistsRoute = "artists_route"
const val artistsRoutePattern = "artists_graph"

fun NavGraphBuilder.artistsGraph(
    navigateToArtist: (String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = artistsRoutePattern,
        startDestination = artistsRoute
    ) {
        composable(route = artistsRoute) {
            ArtistsRoute(
                navigateToArtist = navigateToArtist
            )
        }
        nestedGraphs()
    }
}
