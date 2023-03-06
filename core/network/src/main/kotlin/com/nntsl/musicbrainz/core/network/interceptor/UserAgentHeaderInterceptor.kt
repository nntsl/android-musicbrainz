package com.nntsl.musicbrainz.core.network.interceptor

import com.nntsl.musicbrainz.core.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

private const val USER_AGENT_HEADER = "User-Agent"

@Singleton
class UserAgentHeaderInterceptor : Interceptor {

    private fun getUserAgentString(): String {
        val userAgentContact = BuildConfig.CONTACT_URL
        val appName = BuildConfig.APP_NAME
        val appVersion = BuildConfig.VERSION_CODE
        return "$appName/$appVersion ( $userAgentContact )"
    }

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader(USER_AGENT_HEADER, getUserAgentString())
                    .build()
            )
        }
}
