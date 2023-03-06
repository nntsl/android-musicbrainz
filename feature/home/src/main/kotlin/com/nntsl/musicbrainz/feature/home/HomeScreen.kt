package com.nntsl.musicbrainz.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    navigateToArtist: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.artistsUiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        navigateToArtist = navigateToArtist,
        uiState = uiState
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToArtist: (String) -> Unit,
    uiState: ArtistsUiState
) {
    Box(
        modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        Text("Home", modifier = Modifier
            .fillMaxSize()
            .clickable { navigateToArtist("test") }
        )
    }
}