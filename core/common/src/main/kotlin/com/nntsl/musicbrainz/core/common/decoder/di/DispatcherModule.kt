package com.nntsl.musicbrainz.core.common.decoder.di

import com.nntsl.musicbrainz.core.common.decoder.network.Dispatcher
import com.nntsl.musicbrainz.core.common.decoder.network.MusicbrainzDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Dispatcher(MusicbrainzDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
