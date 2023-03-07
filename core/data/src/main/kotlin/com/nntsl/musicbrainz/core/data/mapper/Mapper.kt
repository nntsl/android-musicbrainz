package com.nntsl.musicbrainz.core.data.mapper

import com.nntsl.musicbrainz.core.domain.model.Album
import com.nntsl.musicbrainz.core.domain.model.Artist
import com.nntsl.musicbrainz.core.network.model.AlbumResponse
import com.nntsl.musicbrainz.core.network.model.AllAlbumsResponse
import com.nntsl.musicbrainz.core.network.model.AllArtistsResponse
import com.nntsl.musicbrainz.core.network.model.ArtistResponse

object ArtistsMapper {

    fun AllArtistsResponse?.toExternalModel(): List<Artist> {
        return this?.artists?.filter { it.id != null }?.map { it.toExternalModel() } ?: listOf()
    }

    private fun ArtistResponse.toExternalModel(): Artist {
        return with(this) {
            Artist(
                id = id ?: "",
                type = type,
                disambiguation = disambiguation,
                score = score,
                name = name,
                country = country,
                area = area?.name,
                beginArea = beginArea?.name,
                endArea = endArea?.name,
                beginDate = lifeSpan?.begin,
                endDate = lifeSpan?.end,
                tags = tags?.mapNotNull { it.name }
            )
        }
    }
}

object AlbumsMapper {

    fun AllAlbumsResponse?.toExternalModel(): List<Album> {
        return this?.releases?.filter { it.id != null }?.map { it.toExternalModel() } ?: listOf()
    }

    private fun AlbumResponse.toExternalModel(): Album {
        return with(this) {
            Album(
                id = id ?: "",
                barcode = barcode,
                country = country,
                date = date,
                score = score,
                title = title,
                disambiguation = disambiguation,
                status = status,
                packaging = packaging,
                trackCount = trackCount
            )
        }
    }
}