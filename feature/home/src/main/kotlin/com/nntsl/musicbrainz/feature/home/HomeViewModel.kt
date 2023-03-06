package com.nntsl.musicbrainz.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nntsl.musicbrainz.core.common.decoder.result.Result
import com.nntsl.musicbrainz.core.common.decoder.result.asResult
import com.nntsl.musicbrainz.core.domain.model.Artist
import com.nntsl.musicbrainz.core.domain.usecase.GetArtistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArtistsUseCase: GetArtistsUseCase
) : ViewModel() {

    val artistsUiState: StateFlow<ArtistsUiState> =
        artistsUiStateStream(
            getArtistsUseCase = getArtistsUseCase,
            "door"
        )
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ArtistsUiState.Loading
            )
}

private fun artistsUiStateStream(
    getArtistsUseCase: GetArtistsUseCase,
    query: String
): Flow<ArtistsUiState> {
    return getArtistsUseCase.invoke(query)
        .asResult()
        .map {
            when (it) {
                is Result.Success -> {
                    ArtistsUiState.Success(it.data)
                }
                is Result.Loading -> {
                    ArtistsUiState.Loading
                }
                is Result.Error -> {
                    ArtistsUiState.Error
                }
            }
        }
}

sealed interface ArtistsUiState {
    object Loading : ArtistsUiState
    data class Success(val artists: List<Artist>) : ArtistsUiState
    object Error : ArtistsUiState
}
