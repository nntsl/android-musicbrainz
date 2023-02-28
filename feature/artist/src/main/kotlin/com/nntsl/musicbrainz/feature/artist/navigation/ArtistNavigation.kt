package com.nntsl.musicbrainz.feature.artist.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nntsl.musicbrainz.core.common.decoder.StringDecoder
import com.nntsl.musicbrainz.feature.artist.ArtistRoute

@VisibleForTesting
const val artistArg = "artist"

internal class ArtistArgs(val artist: String) {
    constructor(savedStateHandle: SavedStateHandle, stringDecoder: StringDecoder) :
            this(stringDecoder.decodeString(checkNotNull(savedStateHandle[artistArg])))
}

fun NavController.navigateToArtist(artist: String) {
    this.navigate("artist_route/$artist")
}

fun NavGraphBuilder.artistScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "artist_route/{$artistArg}",
        arguments = listOf(navArgument(artistArg) { type = NavType.StringType })
    ) {
        ArtistRoute(
            onBackClick = onBackClick
        )
    }
}