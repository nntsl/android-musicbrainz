package com.nntsl.musicbrainz.feature.artists.model

import com.nntsl.musicbrainz.feature.artists.R.string

data class ArtistItem(
    val id: String,
    val type: ArtistType,
    val name: String,
    val score: String,
    val tags: List<String>,
    val country: String,
    val disambiguation: String
)


enum class ArtistType(val stringRes: Int) {
    GROUP(string.group),
    PERSON(string.person)
}
