package com.nntsl.musicbrainz.core.domain.usecase

import com.nntsl.musicbrainz.core.domain.model.Album
import com.nntsl.musicbrainz.core.domain.repository.ArtistAlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val albumsRepository: ArtistAlbumsRepository
) {
    operator fun invoke(query: String): Flow<List<Album>> {
        return albumsRepository.getAlbums(query)
    }
}
