package com.nntsl.musicbrainz.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumResponse(
    val id: String?,
    val score: Int?,
    val title: String?,
    val status: String?,
    val disambiguation: String?,
    val packaging: String?,
    val date: String?,
    val country: String?,
    val barcode: String?,
    @SerialName("track-count")
    val trackCount: Int?
)
