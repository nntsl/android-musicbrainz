package com.nntsl.musicbrainz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nntsl.musicbrainz.feature.artists.navigation.artistsGraph
import com.nntsl.musicbrainz.feature.artists.navigation.artistsRoutePattern

@Composable
fun MusicbrainzNavHost(
    navController: NavHostController,
    isExpandedScreen: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = artistsRoutePattern
    ) {
        artistsGraph(
            isExpandedScreen = isExpandedScreen
        )
    }
}
