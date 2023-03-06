package com.nntsl.musicbrainz.core.domain.repository

import com.nntsl.musicbrainz.core.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface ArtistAlbumsRepository {

    fun getAlbums(query: String): Flow<List<Album>>
}
