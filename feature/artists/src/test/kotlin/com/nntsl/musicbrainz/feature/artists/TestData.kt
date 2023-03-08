package com.nntsl.musicbrainz.feature.artists

import com.nntsl.musicbrainz.core.domain.model.Album
import com.nntsl.musicbrainz.core.domain.model.Artist
import com.nntsl.musicbrainz.feature.artists.model.AlbumItem
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

val testInputAlbums = listOf(
    Album(
        id = "1",
        title = "album 1",
        country = "country 1",
        disambiguation = "disambiguation 1",
        score = 100,
        status = "status 1",
        barcode = "barcode 2",
        date = "date 2",
        packaging = "packaging 2",
        trackCount = 10
    ),
    Album(
        id = "2",
        title = "album 2",
        country = "country 2",
        disambiguation = "disambiguation 2",
        score = 90,
        status = "status 2",
        barcode = "barcode 1",
        date = "date 1",
        packaging = "packaging 1",
        trackCount = 20
    ),
    Album(
        id = "3",
        title = "album 3",
        country = "country 3",
        disambiguation = "disambiguation 3",
        score = 80,
        status = "status 3",
        barcode = "barcode 3",
        date = "date 3",
        packaging = "packaging 3",
        trackCount = 30
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

val testInputAlbumsItems = testInputAlbums.map { album ->
    with(album) {
        AlbumItem(
            id = id,
            title = title ?: "",
            score = score?.toString(),
            country = country,
            disambiguation = disambiguation,
            packaging = packaging,
            barcode = barcode,
            tracks = trackCount?.toString(),
            date = date,
            status = status
        )
    }
}