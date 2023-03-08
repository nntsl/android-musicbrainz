package com.nntsl.musicbrainz.core.data.repository.fake

import com.nntsl.musicbrainz.core.common.decoder.network.Dispatcher
import com.nntsl.musicbrainz.core.common.decoder.network.MusicbrainzDispatchers
import com.nntsl.musicbrainz.core.data.mapper.AlbumsMapper.toExternalModel
import com.nntsl.musicbrainz.core.domain.model.Album
import com.nntsl.musicbrainz.core.domain.repository.ArtistAlbumsRepository
import com.nntsl.musicbrainz.core.network.fake.FakeMusicbrainzNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FakeArtistAlbumsRepositoryImpl @Inject constructor(
    @Dispatcher(MusicbrainzDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val datasource: FakeMusicbrainzNetworkDataSource
) : ArtistAlbumsRepository {

    override fun getAlbums(query: String): Flow<List<Album>> {
        return flow {
            datasource.getAlbums(formatAlbumsQuery(query))
                ?.toExternalModel()?.let {
                    emit(it)
                }
        }.flowOn(ioDispatcher)
    }
}