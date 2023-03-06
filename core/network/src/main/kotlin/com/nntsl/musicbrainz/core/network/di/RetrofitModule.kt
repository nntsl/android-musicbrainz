package com.nntsl.musicbrainz.core.network.di

import com.nntsl.musicbrainz.core.network.BuildConfig
import com.nntsl.musicbrainz.core.network.interceptor.UserAgentHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun providesUserAgent(): UserAgentHeaderInterceptor = UserAgentHeaderInterceptor()

    @Provides
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    fun okHttpCallFactory(userAgentHeaderInterceptor: UserAgentHeaderInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            userAgentHeaderInterceptor
        )
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }
        )
        .build()
}