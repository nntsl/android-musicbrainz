package com.nntsl.musicbrainz.core.data.testdoubles

import com.nntsl.musicbrainz.core.network.MusicbrainzNetworkDataSource
import com.nntsl.musicbrainz.core.network.fake.FakeMusicbrainzNetworkDataSource
import com.nntsl.musicbrainz.core.network.model.AllAlbumsResponse
import com.nntsl.musicbrainz.core.network.model.AllArtistsResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.serialization.json.Json

@OptIn(ExperimentalCoroutinesApi::class)
class TestMusicbrainzNetworkDataSource : MusicbrainzNetworkDataSource {

    private val source = FakeMusicbrainzNetworkDataSource(
        UnconfinedTestDispatcher(),
        Json { ignoreUnknownKeys = true }
    )

    override suspend fun getArtists(query: String): AllArtistsResponse? = runBlocking {
        source.getArtists(query)
    }

    override suspend fun getAlbums(query: String): AllAlbumsResponse? = runBlocking {
        source.getAlbums(query)
    }
}
