package com.nntsl.musicbrainz.core.data.repository.fake

import com.nntsl.musicbrainz.core.common.decoder.network.Dispatcher
import com.nntsl.musicbrainz.core.common.decoder.network.MusicbrainzDispatchers
import com.nntsl.musicbrainz.core.data.mapper.ArtistsMapper.toExternalModel
import com.nntsl.musicbrainz.core.domain.model.Artist
import com.nntsl.musicbrainz.core.domain.repository.ArtistsRepository
import com.nntsl.musicbrainz.core.network.fake.FakeMusicbrainzNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FakeArtistsRepositoryImpl @Inject constructor(
    @Dispatcher(MusicbrainzDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val datasource: FakeMusicbrainzNetworkDataSource
) : ArtistsRepository {

    override fun getArtists(query: String): Flow<List<Artist>> {
        return flow {
            if (query.isEmpty()) {
                emit(listOf())
            } else {
                emit(datasource.getArtists(query).toExternalModel())
            }
        }.flowOn(ioDispatcher)
    }
}
