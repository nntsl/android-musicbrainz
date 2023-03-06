package com.nntsl.musicbrainz.feature.albums

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
fun ArtistRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: ArtistViewModel = hiltViewModel()
) {
    ArtistScreen(
        modifier = modifier,
        onBackClick = onBackClick
    )
}

@Composable
fun ArtistScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        Text("Artist", modifier = Modifier
            .fillMaxSize()
            .clickable { onBackClick() }
        )
    }
}