package com.nntsl.musicbrainz.feature.artists.screen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.nntsl.musicbrainz.core.designsystem.component.MusicbrainzLoadingWheel
import com.nntsl.musicbrainz.feature.artists.ArtistsList
import com.nntsl.musicbrainz.feature.artists.ArtistsUiState
import com.nntsl.musicbrainz.feature.artists.EmptyScreen
import com.nntsl.musicbrainz.feature.artists.R.string
import com.nntsl.musicbrainz.feature.artists.SearchAppBar
import com.nntsl.musicbrainz.feature.artists.model.ArtistItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArtistsScreen(
    modifier: Modifier = Modifier,
    uiState: ArtistsUiState,
    artistsScrollableState: LazyListState = rememberLazyListState(),
    navigateToArtist: (ArtistItem) -> Unit,
    searchArtist: (String) -> Unit,
    query: String = ""
) {
    Scaffold(
        topBar = {
            SearchAppBar(
                searchArtist = searchArtist,
                currentQuery = query
            )
        },
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding()
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (uiState) {
                is ArtistsUiState.Success -> ArtistsList(
                    artists = uiState.artists,
                    navigateToArtist = navigateToArtist,
                    artistsScrollableState = artistsScrollableState
                )
                is ArtistsUiState.NoData -> {
                    if (!uiState.isArtistsLoading) {
                        EmptyScreen()
                    }
                }
            }

            AnimatedVisibility(
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
                        .align(Alignment.TopCenter)
                        .testTag("artists:loadingWheel"),
                    contentDesc = stringResource(string.loading)
                )
            }
        }
    }
}
