package com.nntsl.musicbrainz.core.data.repository

import com.nntsl.musicbrainz.core.common.decoder.network.Dispatcher
import com.nntsl.musicbrainz.core.common.decoder.network.MusicbrainzDispatchers
import com.nntsl.musicbrainz.core.data.mapper.AlbumsMapper.toExternalModel
import com.nntsl.musicbrainz.core.domain.model.Album
import com.nntsl.musicbrainz.core.domain.repository.ArtistAlbumsRepository
import com.nntsl.musicbrainz.core.network.MusicbrainzNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArtistAlbumsRepositoryImpl @Inject constructor(
    private val network: MusicbrainzNetworkDataSource,
    @Dispatcher(MusicbrainzDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : ArtistAlbumsRepository {

    override fun getAlbums(query: String): Flow<List<Album>> {
        return flow {
            network.getArtistAlbum(formatAlbumsQuery(query))
                ?.toExternalModel()?.let {
                    emit(it)
                }
        }.flowOn(ioDispatcher)
    }

    fun formatAlbumsQuery(artistId: String): String {
        return "arid:$artistId"
    }
}
