package com.nntsl.musicbrainz.core.domain

import com.nntsl.musicbrainz.core.domain.model.Artist
import com.nntsl.musicbrainz.core.domain.usecase.GetArtistsUseCase
import com.nntsl.musicbrainz.core.testing.repository.TestArtistsRepository
import com.nntsl.musicbrainz.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetArtistsUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val artistsRepository = TestArtistsRepository()

    val useCase = GetArtistsUseCase(artistsRepository)

    @Test
    fun whenSearchArtists_allArtistsAreReturned() = runTest {

        val artists = useCase("test")

        artistsRepository.sendArtists(sampleArtists)

        assertEquals(
            sampleArtists,
            artists.first()
        )
    }
}

private val sampleArtists = listOf(
    Artist(
        id = "1",
        name = "test",
        area = "area 1",
        endArea = "end area 1",
        beginArea = "begin area 1",
        beginDate = "begin date 1",
        endDate = "end area 1",
        country = "country 1",
        disambiguation = "disambiguation 1",
        score = 100,
        tags = listOf("tag1-1", "tag1-2"),
        type = "group"
    ),
    Artist(
        id = "2",
        name = "test",
        area = "area 2",
        endArea = "end area 2",
        beginArea = "begin area 2",
        beginDate = "begin date 2",
        endDate = "end area 2",
        country = "country 2",
        disambiguation = "disambiguation 2",
        score = 90,
        tags = listOf("tag2-1", "tag2-2"),
        type = "group"
    )
)