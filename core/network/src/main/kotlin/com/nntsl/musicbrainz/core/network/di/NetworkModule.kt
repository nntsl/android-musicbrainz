package com.nntsl.musicbrainz.core.network.di

import com.nntsl.musicbrainz.core.network.MusicbrainzNetworkDataSource
import com.nntsl.musicbrainz.core.network.retrofit.RetrofitMusicbrainzNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsMyFinNetwork(
        network: RetrofitMusicbrainzNetwork
    ): MusicbrainzNetworkDataSource
}
