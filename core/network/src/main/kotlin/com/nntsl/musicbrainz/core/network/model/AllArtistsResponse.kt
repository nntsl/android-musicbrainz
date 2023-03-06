package com.nntsl.musicbrainz.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AllArtistsResponse(
    val artists: List<ArtistResponse>
)
