package com.nntsl.musicbrainz.core.testing.repository

import com.nntsl.musicbrainz.core.domain.model.Artist
import com.nntsl.musicbrainz.core.domain.repository.ArtistsRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestArtistsRepository: ArtistsRepository {

    private val artistsFlow: MutableSharedFlow<List<Artist>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getArtists(query: String): Flow<List<Artist>> {
        return artistsFlow
    }

    /**
     * A test-only API to allow controlling the list of exchange rates from tests.
     */
    fun sendArtists(artists: List<Artist>) {
        artistsFlow.tryEmit(artists)
    }
}
