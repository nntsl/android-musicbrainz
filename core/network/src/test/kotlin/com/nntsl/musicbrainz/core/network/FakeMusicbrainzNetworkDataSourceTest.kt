package com.nntsl.musicbrainz.core.network

import JvmUnitTestFakeAssetManager
import com.nntsl.musicbrainz.core.network.fake.FakeMusicbrainzNetworkDataSource
import com.nntsl.musicbrainz.core.network.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class FakeMusicbrainzNetworkDataSourceTest {

    private lateinit var subject: FakeMusicbrainzNetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = FakeMusicbrainzNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestFakeAssetManager
        )
    }

    @Test
    fun testDeserializationOfArtists() = runTest(testDispatcher) {
        assertEquals(
            ArtistResponse(
                id = "e56fd97e-c18f-4e5e-9b4d-f9fc21b4973f",
                name = "Fred",
                score = 100,
                type = "Group",
                country = "US",
                disambiguation = "US progressive rock band",
                area = AreaResponse("United States"),
                beginArea = AreaResponse("United States"),
                lifeSpan = LifeSpanResponse(begin = "1969", end = "1974", ended = true)
            ),
            subject.getArtists("")?.artists?.firstOrNull()
        )
    }

    @Test
    fun testDeserializationOfAlbums() = runTest(testDispatcher) {
        assertEquals(
            AlbumResponse(
                id = "383be31c-37a0-4e08-8cda-cbcbbc587ae5",
                title = "Blow Your Mind (Mwah)",
                status = "Official",
                date = "2016-08-26",
                country = "XW",
                trackCount = 1,
                score = 100,
                disambiguation = "disambiguation",
                packaging = "packaging",
                barcode = "barcode"
            ),
            subject.getAlbums("")?.releases?.firstOrNull()
        )
    }
}
