package com.nntsl.musicbrainz.core.data.repository

import com.nntsl.musicbrainz.core.data.mapper.AlbumsMapper.toExternalModel
import com.nntsl.musicbrainz.core.data.testdoubles.TestMusicbrainzNetworkDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ArtistAlbumsRepositoryImplTest {

    private lateinit var subject: ArtistAlbumsRepositoryImpl

    private lateinit var network: TestMusicbrainzNetworkDataSource

    @Before
    fun setup() {
        network = TestMusicbrainzNetworkDataSource()

        subject = ArtistAlbumsRepositoryImpl(
            network = network
        )
    }

    @Test
    fun albumsRepository_albumsStream() = runTest {
        val query = "test"

        val networkAlbums = network.getAlbums(query)
            .toExternalModel()

        assertEquals(
            networkAlbums,
            subject.getAlbums(query).first()
        )
    }
}
