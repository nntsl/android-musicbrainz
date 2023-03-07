package com.nntsl.musicbrainz.core.testing.repository

import com.nntsl.musicbrainz.core.domain.model.Album
import com.nntsl.musicbrainz.core.domain.repository.ArtistAlbumsRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestAlbumsRepository: ArtistAlbumsRepository {

    private val albumsFlow: MutableSharedFlow<List<Album>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getAlbums(query: String): Flow<List<Album>> {
        return albumsFlow
    }

    /**
     * A test-only API to allow controlling the list of albums from tests.
     */
    fun sendAlbums(albums: List<Album>) {
        albumsFlow.tryEmit(albums)
    }
}
