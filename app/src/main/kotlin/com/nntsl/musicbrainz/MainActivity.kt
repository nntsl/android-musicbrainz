package com.nntsl.musicbrainz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nntsl.musicbrainz.core.data.util.NetworkMonitor
import com.nntsl.musicbrainz.core.designsystem.theme.MusicbrainzTheme
import com.nntsl.musicbrainz.ui.MusicbrainzApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicbrainzTheme {
                MusicbrainzApp(
                    networkMonitor = networkMonitor
                )
            }
        }
    }
}
