package com.nntsl.musicbrainz.core.data.test

import com.nntsl.musicbrainz.core.data.di.DataModule
import com.nntsl.musicbrainz.core.data.repository.fake.FakeArtistAlbumsRepositoryImpl
import com.nntsl.musicbrainz.core.data.repository.fake.FakeArtistsRepositoryImpl
import com.nntsl.musicbrainz.core.data.util.NetworkMonitor
import com.nntsl.musicbrainz.core.domain.repository.ArtistAlbumsRepository
import com.nntsl.musicbrainz.core.domain.repository.ArtistsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
interface TestDataModule {
    @Binds
    fun bindsArtistsRepository(
        fakeExchangeRatesRepository: FakeArtistsRepositoryImpl
    ): ArtistsRepository

    @Binds
    fun bindsArtistAlbumsRepository(
        fakeNewsRepository: FakeArtistAlbumsRepositoryImpl
    ): ArtistAlbumsRepository

    @Binds
    fun bindsNetworkMonitor(
        fakeNetworkMonitor: AlwaysOnlineNetworkMonitor
    ): NetworkMonitor
}
