package com.nntsl.musicbrainz.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistResponse(
    val id: String?,
    val type: String?,
    val score: Int?,
    val name: String?,
    val country: String?,
    val area: AreaResponse?,
    @SerialName("begin-area")
    val beginArea: AreaResponse?,
    @SerialName("end-area")
    val endArea: AreaResponse?,
    @SerialName("life-span")
    val lifeSpan: LifeSpanResponse?,
    val tags: List<TagResponse>?
)

@Serializable
data class AreaResponse(
    val type: String?,
    val name: String?
)

@Serializable
data class LifeSpanResponse(
    val begin: String?,
    val end: String?
)

@Serializable
data class TagResponse(
    val name: String?
)
