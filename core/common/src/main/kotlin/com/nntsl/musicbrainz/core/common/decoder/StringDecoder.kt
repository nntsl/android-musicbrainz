package com.nntsl.musicbrainz.core.common.decoder

interface StringDecoder {
    fun decodeString(encodedString: String): String
}
