package com.nntsl.musicbrainz.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.nntsl.musicbrainz.core.data.util.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberMusicbrainzAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MusicbrainzAppState {
    return remember(coroutineScope, networkMonitor) {
        MusicbrainzAppState(coroutineScope, networkMonitor)
    }
}

class MusicbrainzAppState(
    coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor
) {

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
}