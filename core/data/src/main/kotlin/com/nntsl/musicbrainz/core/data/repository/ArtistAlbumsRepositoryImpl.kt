package com.nntsl.musicbrainz.core.data.repository

import com.nntsl.musicbrainz.core.data.mapper.AlbumsMapper.toExternalModel
import com.nntsl.musicbrainz.core.domain.model.Album
import com.nntsl.musicbrainz.core.domain.repository.ArtistAlbumsRepository
import com.nntsl.musicbrainz.core.network.MusicbrainzNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtistAlbumsRepositoryImpl @Inject constructor(
    private val network: MusicbrainzNetworkDataSource
) : ArtistAlbumsRepository {

    override fun getAlbums(query: String): Flow<List<Album>> {
        return flow {
            network.getAlbums(formatAlbumsQuery(query))
                ?.toExternalModel()?.let {
                    emit(it)
                }
        }
    }
}
