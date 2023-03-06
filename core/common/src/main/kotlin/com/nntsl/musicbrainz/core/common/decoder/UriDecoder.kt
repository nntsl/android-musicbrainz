package com.nntsl.musicbrainz.core.common.decoder

import android.net.Uri
import javax.inject.Inject

class UriDecoder @Inject constructor() : StringDecoder {

    override fun decodeString(encodedString: String): String = Uri.decode(encodedString)
}