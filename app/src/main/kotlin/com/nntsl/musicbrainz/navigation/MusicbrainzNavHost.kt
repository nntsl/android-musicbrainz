package com.nntsl.musicbrainz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nntsl.musicbrainz.feature.albums.navigation.artistScreen
import com.nntsl.musicbrainz.feature.albums.navigation.navigateToArtist
import com.nntsl.musicbrainz.feature.artists.navigation.artistsGraph
import com.nntsl.musicbrainz.feature.artists.navigation.artistsRoutePattern

@Composable
fun MusicbrainzNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = artistsRoutePattern
    ) {
        artistsGraph(
            navigateToArtist = { artist ->
                navController.navigateToArtist(artist)
            },
            nestedGraphs = {
                artistScreen(onBackClick = { navController.popBackStack() })
            }
        )
    }
}