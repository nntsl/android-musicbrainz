package com.nntsl.musicbrainz.feature.albums

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nntsl.musicbrainz.core.common.decoder.StringDecoder
import com.nntsl.musicbrainz.core.common.decoder.result.Result
import com.nntsl.musicbrainz.core.common.decoder.result.asResult
import com.nntsl.musicbrainz.core.domain.model.Album
import com.nntsl.musicbrainz.core.domain.usecase.GetAlbumsUseCase
import com.nntsl.musicbrainz.feature.albums.navigation.ArtistArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    stringDecoder: StringDecoder,
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {

    private val artistArgs: ArtistArgs = ArtistArgs(savedStateHandle, stringDecoder)

    val albumsUiState: StateFlow<AlbumsUiState> =
        albumsUiStateStream(
            getAlbumsUseCase = getAlbumsUseCase,
            artistArgs.id
        )
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = AlbumsUiState.Loading
            )
}

private fun albumsUiStateStream(
    getAlbumsUseCase: GetAlbumsUseCase,
    query: String
): Flow<AlbumsUiState> {
    return getAlbumsUseCase.invoke(query)
        .asResult()
        .map {
            when (it) {
                is Result.Success -> {
                    AlbumsUiState.Success(it.data)
                }
                is Result.Loading -> {
                    AlbumsUiState.Loading
                }
                is Result.Error -> {
                    AlbumsUiState.Error
                }
            }
        }
}

sealed interface AlbumsUiState {
    object Loading : AlbumsUiState
    data class Success(val albums: List<Album>) : AlbumsUiState
    object Error : AlbumsUiState
}
