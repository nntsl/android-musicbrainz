package com.nntsl.musicbrainz.feature.artists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nntsl.musicbrainz.feature.artists.model.ArtistItem

@Composable
internal fun ArtistsList(
    modifier: Modifier = Modifier,
    artistsScrollableState: LazyListState,
    artists: List<ArtistItem>,
    navigateToArtist: (ArtistItem) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = artistsScrollableState
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (artists.isNotEmpty()) {
            items(artists) { artist ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 48.dp)
                        .testTag("artists:${artist.id}")
                        .clickable {
                            navigateToArtist(artist)
                        },
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.9f),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = artist.name,
                                fontWeight = FontWeight.W500,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.fillMaxWidth()
                            )
                            artist.disambiguation?.let {
                                Text(
                                    text = artist.disambiguation,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp)
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.End
                        ) {
                            artist.score?.let {
                                Text(
                                    text = artist.score,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                            artist.country?.let {
                                Text(
                                    text = artist.country,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                )
                            }
                        }
                    }
                    Divider(modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}
