package com.nntsl.musicbrainz.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nntsl.musicbrainz.core.data.util.NetworkMonitor
import com.nntsl.musicbrainz.navigation.MusicbrainzNavHost
import com.nntsl.musicbrainz.R.string

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicbrainzApp(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    appState: MusicbrainzAppState = rememberMusicbrainzAppState(
        networkMonitor = networkMonitor,
        windowSizeClass = windowSizeClass,
    )
) {
    Scaffold(
        modifier = Modifier.imePadding()
    ) { padding ->

        val snackbarHostState = remember { SnackbarHostState() }

        val isOffline by appState.isOffline.collectAsStateWithLifecycle()

        val notConnected = stringResource(string.not_connected)
        LaunchedEffect(isOffline) {
            if (isOffline) snackbarHostState.showSnackbar(
                message = notConnected,
                duration = SnackbarDuration.Indefinite
            )
        }

        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            MusicbrainzNavHost(
                navController = appState.navController,
                isExpandedScreen = !appState.isCompactScreen
            )
        }
    }
}
