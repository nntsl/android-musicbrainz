package com.nntsl.musicbrainz.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nntsl.musicbrainz.navigation.MusicbrainzNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicbrainzApp() {
    Scaffold(
        modifier = Modifier.imePadding()
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val navController = rememberNavController()

            MusicbrainzNavHost(
                navController = navController
            )
        }
    }
}
