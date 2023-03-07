package com.nntsl.musicbrainz.feature.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nntsl.musicbrainz.core.common.decoder.result.Result
import com.nntsl.musicbrainz.core.common.decoder.result.asResult
import com.nntsl.musicbrainz.core.domain.usecase.GetAlbumsUseCase
import com.nntsl.musicbrainz.core.domain.usecase.GetArtistsUseCase
import com.nntsl.musicbrainz.feature.artists.model.AlbumItem
import com.nntsl.musicbrainz.feature.artists.model.ArtistItem
import com.nntsl.musicbrainz.feature.artists.model.ArtistsMapper.toUiAlbumsModel
import com.nntsl.musicbrainz.feature.artists.model.ArtistsMapper.toUiArtistsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {

    private val viewModelState = MutableStateFlow(ArtistsViewModelState())

    val artistsUiState: StateFlow<ArtistsUiState> =
        viewModelState
            .map(ArtistsViewModelState::toUiState)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = viewModelState.value.toUiState()
            )

    fun getCurrentQuery(): String = viewModelState.value.query

    fun searchArtists(query: String) {
        viewModelState.update {
            it.copy(
                isAlbumsOpened = false,
                isArtistsLoading = true
            )
        }
        viewModelScope.launch {
            getArtistsUseCase(query).asResult().collect() { result ->
                when (result) {
                    is Result.Success -> {
                        viewModelState.update {
                            it.copy(
                                artists = result.data.toUiArtistsModel(),
                                isArtistsLoading = false,
                                query = query
                            )
                        }
                    }
                    is Result.Loading -> {
                        viewModelState.update {
                            it.copy(
                                query = query,
                                isArtistsLoading = true
                            )
                        }
                    }
                    is Result.Error -> {
                        viewModelState.update {
                            it.copy(
                                artists = listOf(),
                                isArtistsLoading = false,
                                query = query,
                                selectedArtist = null,
                                isAlbumsOpened = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun interactedWithAlbums() {
        viewModelState.update {
            it.copy(isAlbumsOpened = false)
        }
    }

    fun interactedWithArtists(artist: ArtistItem) {
        viewModelState.update {
            it.copy(
                isAlbumsOpened = true,
                isAlbumsLoading = true,
                selectedArtist = artist
            )
        }

        viewModelScope.launch {
            getAlbumsUseCase(artist.id).collect() { albums ->
                viewModelState.update {
                    it.copy(
                        albums = albums.toUiAlbumsModel(),
                        isAlbumsLoading = false
                    )
                }
            }
        }
    }
}

private data class ArtistsViewModelState(
    val isArtistsLoading: Boolean = false,
    val isAlbumsLoading: Boolean = false,
    val artists: List<ArtistItem> = listOf(),
    val selectedArtist: ArtistItem? = null,
    val isAlbumsOpened: Boolean = false,
    val query: String = "",
    val albums: List<AlbumItem> = listOf()
) {
    fun toUiState(): ArtistsUiState {
        return if (artists.isNotEmpty() || query.isEmpty()) {
            ArtistsUiState.Success(
                isArtistsLoading = isArtistsLoading,
                isAlbumsLoading = isAlbumsLoading,
                artists = artists,
                isAlbumsOpened = isAlbumsOpened,
                albums = albums,
                selectedArtist = selectedArtist
            )
        } else {
            ArtistsUiState.NoData(
                isArtistsLoading = isArtistsLoading,
                isAlbumsLoading = isAlbumsLoading
            )
        }
    }
}
