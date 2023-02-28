package com.nntsl.musicbrainz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nntsl.musicbrainz.core.designsystem.theme.MusicbrainzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicbrainzTheme {

            }
        }
    }
}
