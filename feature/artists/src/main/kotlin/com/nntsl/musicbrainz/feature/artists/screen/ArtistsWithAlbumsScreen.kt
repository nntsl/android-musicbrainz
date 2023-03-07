package com.nntsl.musicbrainz.feature.artists.screen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nntsl.musicbrainz.core.designsystem.component.MusicbrainzLoadingWheel
import com.nntsl.musicbrainz.feature.artists.*
import com.nntsl.musicbrainz.feature.artists.R
import com.nntsl.musicbrainz.feature.artists.model.ArtistItem

@Composable
internal fun ArtistsWithAlbumsScreen(
    modifier: Modifier = Modifier,
    uiState: ArtistsUiState,
    navigateToArtist: (ArtistItem) -> Unit,
    albumsLazyListStates: Map<String, LazyListState>,
    artistsScrollableState: LazyListState = rememberLazyListState(),
    searchArtist: (String) -> Unit,
    query: String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .width(400.dp)
            ) {
                SearchAppBar(
                    searchArtist = searchArtist,
                    currentQuery = query
                )
                Box() {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = uiState.isArtistsLoading,
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight -> -fullHeight },
                        ) + fadeIn(),
                        exit = slideOutVertically(
                            targetOffsetY = { fullHeight -> -fullHeight },
                        ) + fadeOut(),
                    ) {
                        MusicbrainzLoadingWheel(
                            modifier = modifier
                                .fillMaxWidth()
                                .testTag("artists:loadingWheel"),
                            contentDesc = stringResource(R.string.loading)
                        )
                    }
                    when (uiState) {
                        is ArtistsUiState.Success -> {
                            ArtistsList(
                                artists = uiState.artists,
                                navigateToArtist = navigateToArtist,
                                artistsScrollableState = artistsScrollableState
                            )
                        }
                        else -> {
                            if (!uiState.isArtistsLoading) {
                                EmptyScreen()
                            }
                        }
                    }
                }
            }
            if (uiState is ArtistsUiState.Success && uiState.isAlbumsOpened) {
                Crossfade(targetState = uiState.selectedArtist) { selectedArtist ->
                    val selectedLazyListState by remember {
                        derivedStateOf {
                            albumsLazyListStates.getValue(selectedArtist?.id ?: "")
                        }
                    }
                    selectedArtist?.let {
                        key(it.id) {
                            AlbumsScreen(
                                lazyListState = selectedLazyListState,
                                isExpandedScreen = true,
                                onBackClick = {},
                                uiState = uiState
                            )
                        }
                    }
                }
            }
        }
    }
}
