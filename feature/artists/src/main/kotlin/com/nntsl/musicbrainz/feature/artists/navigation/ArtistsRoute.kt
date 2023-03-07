package com.nntsl.musicbrainz.feature.artists.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nntsl.musicbrainz.feature.artists.screen.AlbumsScreen
import com.nntsl.musicbrainz.feature.artists.screen.ArtistsScreen
import com.nntsl.musicbrainz.feature.artists.ArtistsUiState
import com.nntsl.musicbrainz.feature.artists.ArtistsViewModel
import com.nntsl.musicbrainz.feature.artists.screen.ArtistsWithAlbumsScreen

@Composable
fun ArtistsRoute(
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean,
    viewModel: ArtistsViewModel = hiltViewModel()
) {
    val uiState by viewModel.artistsUiState.collectAsStateWithLifecycle()
    val artistsScrollableState = rememberLazyListState()
    val albumsLazyListStates = when (uiState) {
        is ArtistsUiState.Success -> (uiState as ArtistsUiState.Success).artists
        else -> emptyList()
    }.associate { artist ->
        key(artist.id) {
            artist.id to rememberLazyListState()
        }
    }

    when (getArtistsScreenType(isExpandedScreen, uiState)) {
        ArtistsScreenType.ArtistsWithAlbums -> {
            ArtistsWithAlbumsScreen(
                modifier = modifier,
                uiState = uiState,
                navigateToArtist = viewModel::interactedWithArtists,
                albumsLazyListStates = albumsLazyListStates,
                searchArtist = viewModel::searchArtists,
                query = viewModel.getCurrentQuery()
            )
        }
        ArtistsScreenType.Artists -> {
            ArtistsScreen(
                modifier = modifier,
                uiState = uiState,
                artistsScrollableState = artistsScrollableState,
                navigateToArtist = viewModel::interactedWithArtists,
                searchArtist = viewModel::searchArtists,
                query = viewModel.getCurrentQuery()
            )
        }
        ArtistsScreenType.Albums -> {
            check(uiState is ArtistsUiState.Success)
            (uiState as ArtistsUiState.Success).selectedArtist?.let {
                AlbumsScreen(
                    modifier = modifier,
                    uiState = uiState,
                    onBackClick = viewModel::interactedWithAlbums,
                    isExpandedScreen = isExpandedScreen,
                    lazyListState = albumsLazyListStates.getValue(it.id),
                )
            }
            BackHandler {
                viewModel.interactedWithAlbums()
            }
        }
    }
}

private enum class ArtistsScreenType {
    ArtistsWithAlbums,
    Artists,
    Albums
}

@Composable
private fun getArtistsScreenType(
    isExpandedScreen: Boolean,
    uiState: ArtistsUiState
): ArtistsScreenType = when (isExpandedScreen) {
    false -> {
        when (uiState) {
            is ArtistsUiState.Success -> {
                if (uiState.isAlbumsOpened) {
                    ArtistsScreenType.Albums
                } else {
                    ArtistsScreenType.Artists
                }
            }
            else -> ArtistsScreenType.Artists
        }
    }
    true -> ArtistsScreenType.ArtistsWithAlbums
}
