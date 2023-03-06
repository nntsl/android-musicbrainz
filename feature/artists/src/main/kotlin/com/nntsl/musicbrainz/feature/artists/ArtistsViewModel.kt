package com.nntsl.musicbrainz.feature.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nntsl.musicbrainz.core.common.decoder.result.Result
import com.nntsl.musicbrainz.core.common.decoder.result.asResult
import com.nntsl.musicbrainz.core.domain.usecase.GetArtistsUseCase
import com.nntsl.musicbrainz.feature.artists.model.ArtistItem
import com.nntsl.musicbrainz.feature.artists.model.ArtistType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val getArtistsUseCase: GetArtistsUseCase
) : ViewModel() {

    private val currentQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val artistsUiState: StateFlow<ArtistsUiState> =
        currentQuery
            .map {
                artistsUiStateStream(getArtistsUseCase = getArtistsUseCase, query = it)
            }
            .flatMapLatest { it }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ArtistsUiState.Loading
            )

    fun searchArtist(query: String) {
        viewModelScope.launch {
            currentQuery.value = query
        }
    }
}

private fun artistsUiStateStream(
    getArtistsUseCase: GetArtistsUseCase,
    query: String
): Flow<ArtistsUiState> {
    return getArtistsUseCase(query)
        .asResult()
        .map {
            when (it) {
                is Result.Success -> {
                    if (it.data.isNotEmpty()) {
                        ArtistsUiState.Success(
                            it.data.filter { it.name != null }
                                .map { artist ->
                                    with(artist) {
                                        ArtistItem(
                                            id = id,
                                            type = if (type.equals("group", ignoreCase = true)) {
                                                ArtistType.GROUP
                                            } else {
                                                ArtistType.PERSON
                                            },
                                            name = name ?: "",
                                            score = score?.toString() ?: "",
                                            tags = tags ?: listOf(),
                                            country = country ?: "",
                                            disambiguation = disambiguation ?: ""
                                        )
                                    }
                                })
                    } else {
                        ArtistsUiState.NoData
                    }
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
