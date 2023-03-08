package com.nntsl.musicbrainz.core.data.repository

import com.nntsl.musicbrainz.core.data.mapper.ArtistsMapper.toExternalModel
import com.nntsl.musicbrainz.core.domain.model.Artist
import com.nntsl.musicbrainz.core.domain.repository.ArtistsRepository
import com.nntsl.musicbrainz.core.network.MusicbrainzNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtistsRepositoryImpl @Inject constructor(
    private val network: MusicbrainzNetworkDataSource
) : ArtistsRepository {

    override fun getArtists(query: String): Flow<List<Artist>> {
        return flow {
            if (query.isEmpty()) {
                emit(listOf())
            } else {
                emit(network.getArtists(query).toExternalModel())
            }
        }
    }
}
