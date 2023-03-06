package com.nntsl.musicbrainz.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AllAlbumsResponse(
    val releases: List<AlbumResponse>?
)
