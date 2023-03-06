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
        return Artist(
            id = this.id ?: "",
            type = this.type,
            score = this.score,
            name = this.name,
            country = this.country,
            area = this.area?.name,
            beginArea = this.beginArea?.name,
            endArea = this.endArea?.name,
            beginDate = this.lifeSpan?.begin,
            endDate = this.lifeSpan?.end,
            tags = this.tags?.map { it.name }
        )
    }
}

object AlbumsMapper {

    fun AllAlbumsResponse?.toExternalModel(): List<Album> {
        return this?.releases?.filter { it.id != null }?.map { it.toExternalModel() } ?: listOf()
    }

    private fun AlbumResponse.toExternalModel(): Album {
        return Album(
            id = this.id ?: "",
            barcode = this.barcode,
            country = this.country,
            date = this.date,
            score = this.score,
            title = this.title,
            disambiguation = this.disambiguation,
            status = this.status,
            packaging = this.packaging,
            trackCount = this.trackCount
        )
    }
}