package com.nntsl.musicbrainz.core.common.decoder.network

@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val musicbrainzDispatcher: MusicbrainzDispatchers)

enum class MusicbrainzDispatchers {
    IO
}
