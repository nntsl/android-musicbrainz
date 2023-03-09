package com.nntsl.musicbrainz.core.data.repository

import com.nntsl.musicbrainz.core.data.mapper.ArtistsMapper.toExternalModel
import com.nntsl.musicbrainz.core.data.testdoubles.TestMusicbrainzNetworkDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ArtistsRepositoryImplTest {

    private lateinit var subject: ArtistsRepositoryImpl

    private lateinit var network: TestMusicbrainzNetworkDataSource

    @Before
    fun setup() {
        network = TestMusicbrainzNetworkDataSource()

        subject = ArtistsRepositoryImpl(
            network = network
        )
    }

    @Test
    fun artistsRepository_artistsStream() = runTest {
        val query = "test"

        val networkArtists = network.getArtists(query)
            .toExternalModel()

        assertEquals(
            networkArtists,
            subject.getArtists(query).first()
        )
    }
}
