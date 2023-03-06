package com.nntsl.musicbrainz.core.data.repository

import com.nntsl.musicbrainz.core.common.decoder.network.Dispatcher
import com.nntsl.musicbrainz.core.common.decoder.network.MusicbrainzDispatchers
import com.nntsl.musicbrainz.core.data.mapper.ArtistsMapper.toExternalModel
import com.nntsl.musicbrainz.core.domain.model.Artist
import com.nntsl.musicbrainz.core.domain.repository.ArtistsRepository
import com.nntsl.musicbrainz.core.network.MusicbrainzNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArtistsRepositoryImpl @Inject constructor(
    private val network: MusicbrainzNetworkDataSource,
    @Dispatcher(MusicbrainzDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : ArtistsRepository {

    override fun getArtists(query: String): Flow<List<Artist>> {
        return flow {
            emit(network.getArtists(query).toExternalModel())
        }.flowOn(ioDispatcher)
    }
}
