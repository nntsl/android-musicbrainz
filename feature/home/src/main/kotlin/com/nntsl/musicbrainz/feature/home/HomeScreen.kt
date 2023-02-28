package com.nntsl.musicbrainz.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    navigateToArtist: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    HomeScreen(
        modifier = modifier,
        navigateToArtist = navigateToArtist
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToArtist: (String) -> Unit
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