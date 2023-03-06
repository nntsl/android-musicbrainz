package com.nntsl.musicbrainz.core.network

import com.nntsl.musicbrainz.core.network.model.AllAlbumsResponse
import com.nntsl.musicbrainz.core.network.model.AllArtistsResponse

interface MusicbrainzNetworkDataSource {

    suspend fun getArtists(query: String): AllArtistsResponse?

    suspend fun getArtistAlbum(query: String): AllAlbumsResponse?
}
