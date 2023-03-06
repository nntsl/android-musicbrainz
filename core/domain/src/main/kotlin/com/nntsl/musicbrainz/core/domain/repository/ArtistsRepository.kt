package com.nntsl.musicbrainz.core.domain.repository

import com.nntsl.musicbrainz.core.domain.model.Artist
import kotlinx.coroutines.flow.Flow

interface ArtistsRepository {

    fun getArtists(query: String): Flow<List<Artist>>
}