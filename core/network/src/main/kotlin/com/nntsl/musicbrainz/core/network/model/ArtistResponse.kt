package com.nntsl.musicbrainz.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistResponse(
    val id: String?,
    val type: String? = null,
    val disambiguation: String? = null,
    val score: Int? = null,
    val name: String?,
    val country: String? = null,
    val area: AreaResponse? = null,
    @SerialName("begin-area")
    val beginArea: AreaResponse? = null,
    @SerialName("end-area")
    val endArea: AreaResponse? = null,
    @SerialName("life-span")
    val lifeSpan: LifeSpanResponse? = null,
    val tags: List<TagResponse>? = null
)

@Serializable
data class AreaResponse(
    val type: String?,
    val name: String?
)

@Serializable
data class LifeSpanResponse(
    val begin: String? = null,
    val end: String? = null,
    val ended: Boolean? = null
)

@Serializable
data class TagResponse(
    val name: String?
)
