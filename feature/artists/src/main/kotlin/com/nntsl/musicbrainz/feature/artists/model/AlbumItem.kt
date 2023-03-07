package com.nntsl.musicbrainz.feature.artists.model

data class AlbumItem(
    val id: String,
    val score: String?,
    val title: String,
    val status: String?,
    val disambiguation: String?,
    val packaging: String?,
    val date: String?,
    val country: String?,
    val tracks: String?,
    val barcode: String?
)
