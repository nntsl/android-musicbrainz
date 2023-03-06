package com.nntsl.musicbrainz.core.data.di

import com.nntsl.musicbrainz.core.data.repository.ArtistAlbumsRepositoryImpl
import com.nntsl.musicbrainz.core.data.repository.ArtistsRepositoryImpl
import com.nntsl.musicbrainz.core.data.util.ConnectivityManagerNetworkMonitor
import com.nntsl.musicbrainz.core.domain.repository.ArtistAlbumsRepository
import com.nntsl.musicbrainz.core.domain.repository.ArtistsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.nntsl.musicbrainz.core.data.util.NetworkMonitor

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsArtistsRepository(
        artistsRepository: ArtistsRepositoryImpl
    ): ArtistsRepository

    @Binds
    fun bindsArtistAlbumsRepository(
        artistAlbumsRepository: ArtistAlbumsRepositoryImpl
    ): ArtistAlbumsRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor
}
