package com.nntsl.musicbrainz.core.common.decoder.di

import com.nntsl.musicbrainz.core.common.decoder.StringDecoder
import com.nntsl.musicbrainz.core.common.decoder.UriDecoder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StringDecoderModule {
    @Binds
    abstract fun bindStringDecoder(uriDecoder: UriDecoder): StringDecoder
}