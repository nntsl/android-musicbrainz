package com.nntsl.musicbrainz.core.domain

import com.nntsl.musicbrainz.core.domain.model.Album
import com.nntsl.musicbrainz.core.domain.usecase.GetAlbumsUseCase
import com.nntsl.musicbrainz.core.testing.repository.TestAlbumsRepository
import com.nntsl.musicbrainz.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class GetAlbumsUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val albumsRepository = TestAlbumsRepository()

    val useCase = GetAlbumsUseCase(albumsRepository)

    @Test
    fun whenSearchArtists_allArtistsAreReturned() = runTest {

        val albums = useCase("album")

        albumsRepository.sendAlbums(sampleAlbums)

        assertEquals(
            sampleAlbums,
            albums.first()
        )
    }
}

private val sampleAlbums = listOf(
    Album(
        id = "1",
        title = "album 1",
        country = "country 1",
        disambiguation = "disambiguation 1",
        score = 100,
        status = "status 1",
        barcode = "barcode 2",
        date = "date 2",
        packaging = "packaging 2",
        trackCount = 10
    ),
    Album(
        id = "2",
        title = "album 2",
        country = "country 2",
        disambiguation = "disambiguation 2",
        score = 90,
        status = "status 2",
        barcode = "barcode 1",
        date = "date 1",
        packaging = "packaging 1",
        trackCount = 20
    )
)
