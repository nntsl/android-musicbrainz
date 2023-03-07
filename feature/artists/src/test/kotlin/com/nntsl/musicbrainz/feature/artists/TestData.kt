package com.nntsl.musicbrainz.feature.artists

import com.nntsl.musicbrainz.core.domain.model.Artist
import com.nntsl.musicbrainz.feature.artists.model.ArtistItem
import com.nntsl.musicbrainz.feature.artists.model.ArtistType

val testInputArtists = listOf(
    Artist(
        id = "1",
        name = "test",
        area = "area 1",
        endArea = "end area 1",
        beginArea = "begin area 1",
        beginDate = "begin date 1",
        endDate = "end area 1",
        country = "country 1",
        disambiguation = "disambiguation 1",
        score = 100,
        tags = listOf("tag1", "tag2"),
        type = "group"
    ),
    Artist(
        id = "2",
        name = "test",
        area = "area 2",
        endArea = "end area 2",
        beginArea = "begin area 2",
        beginDate = "begin date 2",
        endDate = "end area 2",
        country = "country 2",
        disambiguation = "disambiguation 2",
        score = 90,
        tags = listOf("tag2-1", "tag2-2"),
        type = "group"
    ),
    Artist(
        id = "3",
        name = "test 2",
        area = "area 3",
        endArea = "end area 3",
        beginArea = "begin area 3",
        beginDate = "begin date 3",
        endDate = "end area 3",
        country = "country 3",
        disambiguation = "disambiguation 3",
        score = 100,
        tags = listOf("tag3-1", "tag3-2"),
        type = ""
    )
)

val testInputArtistsItems = testInputArtists.map { artist ->
    with(artist) {
        ArtistItem(
            id = id,
            type = if (type.equals("group", ignoreCase = true)) {
                ArtistType.GROUP
            } else {
                ArtistType.PERSON
            },
            name = name ?: "",
            score = score?.toString(),
            tags = tags ?: listOf(),
            country = country,
            disambiguation = disambiguation,
            beginDate = beginDate,
            endDate = endDate
        )
    }
}