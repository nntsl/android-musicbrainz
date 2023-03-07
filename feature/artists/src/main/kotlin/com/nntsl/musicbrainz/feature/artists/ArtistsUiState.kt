package com.nntsl.musicbrainz.feature.artists

import com.nntsl.musicbrainz.feature.artists.model.AlbumItem
import com.nntsl.musicbrainz.feature.artists.model.ArtistItem

sealed interface ArtistsUiState {

    val isArtistsLoading: Boolean
    val isAlbumsLoading: Boolean

    data class Success(
        override val isArtistsLoading: Boolean,
        override val isAlbumsLoading: Boolean,
        val artists: List<ArtistItem> = listOf(),
        val isAlbumsOpened: Boolean = false,
        val selectedArtist: ArtistItem? = null,
        val albums: List<AlbumItem> = listOf()
    ) : ArtistsUiState


    data class NoData(
        override val isArtistsLoading: Boolean,
        override val isAlbumsLoading: Boolean
    ) : ArtistsUiState
}
