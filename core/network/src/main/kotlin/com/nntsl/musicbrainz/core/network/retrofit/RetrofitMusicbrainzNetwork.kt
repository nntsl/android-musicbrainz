package com.nntsl.musicbrainz.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nntsl.musicbrainz.core.network.BuildConfig
import com.nntsl.musicbrainz.core.network.MusicbrainzNetworkDataSource
import com.nntsl.musicbrainz.core.network.model.AllAlbumsResponse
import com.nntsl.musicbrainz.core.network.model.AllArtistsResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitMusicbrainzNetworkApi {

    @GET("artist")
    suspend fun getArtists(
        @Query("query") query: String,
        @Query("fmt") format: String = "json"
    ): AllArtistsResponse?

    @GET("release")
    suspend fun getAlbums(
        @Query("query") query: String,
        @Query("fmt") format: String = "json"
    ): AllAlbumsResponse?
}

@Singleton
class RetrofitMusicbrainzNetwork @Inject constructor(
    networkJson: Json,
    okHttpClient: OkHttpClient,
) : MusicbrainzNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    private val networkApi by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .client(okHttpClient)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RetrofitMusicbrainzNetworkApi::class.java)
    }

    override suspend fun getArtists(query: String): AllArtistsResponse? {
        return networkApi.getArtists(query)
    }

    override suspend fun getAlbums(query: String): AllAlbumsResponse? {
        return networkApi.getAlbums(query)
    }
}