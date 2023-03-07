package com.nntsl.musicbrainz.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumResponse(
    val id: String?,
    val score: Int? = null,
    val title: String? = null,
    val status: String? = null,
    val disambiguation: String? = null,
    val packaging: String? = null,
    val date: String? = null,
    val country: String? = null,
    val barcode: String? = null,
    @SerialName("track-count")
    val trackCount: Int? = null
)
