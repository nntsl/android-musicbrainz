package com.nntsl.musicbrainz.feature.artists.model

import com.nntsl.musicbrainz.core.domain.model.Album
import com.nntsl.musicbrainz.core.domain.model.Artist

object ArtistsMapper {

    fun List<Artist>.toUiArtistsModel(): List<ArtistItem> {
        return this.map {
            with(it) {
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
    }

    fun List<Album>.toUiAlbumsModel(): List<AlbumItem> {
        return this.map {
            with(it) {
                AlbumItem(
                    id = id,
                    title = title ?: "",
                    score = score?.toString(),
                    country = country,
                    disambiguation = disambiguation,
                    date = date,
                    packaging = packaging,
                    status = status,
                    tracks = trackCount?.toString(),
                    barcode = barcode
                )
            }
        }
    }
}
