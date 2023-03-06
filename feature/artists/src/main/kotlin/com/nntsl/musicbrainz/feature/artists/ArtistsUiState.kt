package com.nntsl.musicbrainz.feature.artists

import com.nntsl.musicbrainz.feature.artists.model.ArtistItem

sealed interface ArtistsUiState {
    data class Success(val artists: List<ArtistItem>) : ArtistsUiState
    object Loading: ArtistsUiState
    object Error: ArtistsUiState
    object NoData: ArtistsUiState
}
