package com.nntsl.musicbrainz.core.domain.usecase

import com.nntsl.musicbrainz.core.domain.model.Artist
import com.nntsl.musicbrainz.core.domain.repository.ArtistsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistsUseCase @Inject constructor(
    private val artistsRepository: ArtistsRepository
) {
    operator fun invoke(query: String): Flow<List<Artist>> {
        return artistsRepository.getArtists(query)
    }
}
