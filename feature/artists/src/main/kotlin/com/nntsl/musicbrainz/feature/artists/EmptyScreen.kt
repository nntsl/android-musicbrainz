package com.nntsl.musicbrainz.feature.artists

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
internal fun EmptyScreen() {
    Text(
        text = stringResource(R.string.empty_header),
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.bodyMedium
    )
}
