package com.nntsl.musicbrainz.feature.artists

import com.nntsl.musicbrainz.feature.artists.model.ArtistItem
import com.nntsl.musicbrainz.feature.artists.model.ArtistType

val previewArtists = listOf(
    ArtistItem(
        id = "1",
        name = "test 1",
        country = "US",
        score = "100",
        tags = listOf("tag1-1", "tag1-2"),
        disambiguation = "",
        type = ArtistType.GROUP
    ),
    ArtistItem(
        id = "3",
        name = "test 2",
        country = "GB",
        score = "90",
        tags = listOf("tag2-1", "tag2-2"),
        disambiguation = "",
        type = ArtistType.PERSON
    )
)