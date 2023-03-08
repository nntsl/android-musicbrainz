package com.nntsl.musicbrainz.core.network.fake

import com.nntsl.musicbrainz.core.common.decoder.network.Dispatcher
import com.nntsl.musicbrainz.core.common.decoder.network.MusicbrainzDispatchers
import JvmUnitTestFakeAssetManager
import com.nntsl.musicbrainz.core.network.MusicbrainzNetworkDataSource
import com.nntsl.musicbrainz.core.network.model.AllAlbumsResponse
import com.nntsl.musicbrainz.core.network.model.AllArtistsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

@OptIn(ExperimentalSerializationApi::class)
class FakeMusicbrainzNetworkDataSource @Inject constructor(
    @Dispatcher(MusicbrainzDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager
) : MusicbrainzNetworkDataSource {

    companion object {
        private const val ARTISTS_ASSET = "artists.json"
        private const val ALBUMS_ASSET = "albums.json"
    }

    override suspend fun getArtists(query: String): AllArtistsResponse? {
        return withContext(ioDispatcher) {
            assets.open(ARTISTS_ASSET).use(networkJson::decodeFromStream)
        }
    }

    override suspend fun getAlbums(query: String): AllAlbumsResponse? {
        return withContext(ioDispatcher) {
            assets.open(ALBUMS_ASSET).use(networkJson::decodeFromStream)
        }
    }
}
