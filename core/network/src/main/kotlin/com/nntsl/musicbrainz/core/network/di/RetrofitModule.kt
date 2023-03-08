package com.nntsl.musicbrainz.core.network.di

import android.content.Context
import com.nntsl.musicbrainz.core.network.BuildConfig
import com.nntsl.musicbrainz.core.network.fake.FakeAssetManager
import com.nntsl.musicbrainz.core.network.interceptor.UserAgentHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun providesUserAgent(): UserAgentHeaderInterceptor = UserAgentHeaderInterceptor()

    @Provides
    @Singleton
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

    @Provides
    @Singleton
    fun providesFakeAssetManager(
        @ApplicationContext context: Context,
    ): FakeAssetManager = FakeAssetManager(context.assets::open)
}