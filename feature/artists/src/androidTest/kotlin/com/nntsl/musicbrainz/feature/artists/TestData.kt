package com.nntsl.musicbrainz.feature.artists

import com.nntsl.musicbrainz.feature.artists.model.AlbumItem
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
        type = ArtistType.GROUP,
        beginDate = "beginDate 1",
        endDate = "endDate 1"
    ),
    ArtistItem(
        id = "3",
        name = "test 2",
        country = "GB",
        score = "90",
        tags = listOf("tag2-1", "tag2-2"),
        disambiguation = "",
        type = ArtistType.PERSON,
        beginDate = "beginDate 1",
        endDate = null
    )
)

val previewAlbums = listOf(
    AlbumItem(
        id = "album 1",
        title = "album title 1",
        score = "album score 1",
        country = "album country 1",
        disambiguation = "album disambiguation 1",
        packaging = "album packaging 1",
        barcode = null,
        tracks = "album tracks 1",
        date = "album date 1",
        status = "album status 1"
    ),
    AlbumItem(
        id = "album 2",
        title = "album title 2",
        score = "album score 2",
        country = "album country 2",
        disambiguation = "album disambiguation 2",
        packaging = null,
        barcode = "album barcode 2",
        tracks = "album tracks 2",
        date = "album date 2",
        status = "album status 2"
    )
)