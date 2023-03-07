package com.nntsl.musicbrainz.feature.artists.screen

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.nntsl.musicbrainz.core.designsystem.component.MusicbrainzLoadingWheel
import com.nntsl.musicbrainz.core.designsystem.icon.MusicbrainzIcons
import com.nntsl.musicbrainz.feature.artists.ArtistsUiState
import com.nntsl.musicbrainz.feature.artists.R.string
import com.nntsl.musicbrainz.feature.artists.model.AlbumItem
import com.nntsl.musicbrainz.feature.artists.model.ArtistItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    uiState: ArtistsUiState,
    isExpandedScreen: Boolean,
    lazyListState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            AlbumsToolbar(
                isExpandedScreen = isExpandedScreen,
                onBackClick = onBackClick,
                artist = if (uiState is ArtistsUiState.Success) {
                    uiState.selectedArtist
                } else null
            )
        }
        item {
            Box() {
                AnimatedVisibility(
                    visible = uiState.isArtistsLoading,
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> -fullHeight },
                    ) + fadeIn(),
                    exit = slideOutVertically(
                        targetOffsetY = { fullHeight -> -fullHeight },
                    ) + fadeOut(),
                ) {
                    MusicbrainzLoadingWheel(
                        modifier = modifier
                            .fillMaxWidth()
                            .testTag("albums:loadingWheel"),
                        contentDesc = stringResource(string.loading)
                    )
                }
                if (uiState is ArtistsUiState.Success) {
                    uiState.selectedArtist?.let {
                        ArtistDetails(
                            artist = it
                        )
                        with(this@LazyColumn) {
                            artistAlbums(
                                albums = uiState.albums
                            )
                        }
                    }
                }
            }
        }
        item {
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
        }
    }
}

@Composable
private fun AlbumsToolbar(
    modifier: Modifier = Modifier,
    artist: ArtistItem?,
    isExpandedScreen: Boolean,
    onBackClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        if (!isExpandedScreen) {
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier.testTag("albums:back")
            ) {
                Icon(
                    imageVector = MusicbrainzIcons.ArrowBack,
                    contentDescription = stringResource(string.back)
                )
            }
        }
        Text(
            text = artist?.name ?: "",
            fontWeight = FontWeight.W600,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .testTag("albums:artistName")
                .padding(
                    start = 16.dp,
                    top = if (isExpandedScreen) 32.dp else 0.dp,
                    end = 16.dp,
                    bottom = if (isExpandedScreen) 20.dp else 0.dp
                )
        )
    }
}

@Composable
private fun ArtistDetails(
    modifier: Modifier = Modifier,
    artist: ArtistItem,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        ItemWithValue(type = string.type, value = stringResource(artist.type.stringRes))
        ItemWithValue(type = string.score, value = artist.score)
        ItemWithValue(type = string.country, value = artist.country)
        ItemWithValue(type = string.begin_date, value = artist.beginDate)
        artist.endDate?.let {
            ItemWithValue(type = string.end_date, value = it)
        }

        if (artist.tags.isNotEmpty()) {
            FlowRow(
                crossAxisSpacing = 8.dp,
                mainAxisSpacing = 8.dp,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                artist.tags.forEach { tag ->
                    Box(
                        Modifier
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant
                            )
                    ) {
                        Text(
                            text = tag,
                            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

private fun LazyListScope.artistAlbums(
    modifier: Modifier = Modifier,
    albums: List<AlbumItem>,
) {
    if (albums.isNotEmpty()) {
        item {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 12.dp)
            ) {
                Divider()
                Text(
                    stringResource(string.albums),
                    fontWeight = FontWeight.W600,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                )
                Divider()
            }
        }
        items(albums) { album ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("albums:${album.id}")
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    .background(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(
                        text = album.title,
                        fontWeight = FontWeight.W500,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    ItemWithValue(type = string.status, value = album.status)
                    ItemWithValue(type = string.tracks, value = album.tracks)
                    ItemWithValue(type = string.date, value = album.date)
                    ItemWithValue(type = string.country, value = album.country)
                    ItemWithValue(type = string.score, value = album.score)
                    ItemWithValue(type = string.packaging, value = album.packaging)
                    ItemWithValue(type = string.barcode, value = album.barcode)
                }
            }
        }
    }
}

@Composable
private fun ItemWithValue(type: Int, value: String?) {
    FlowRow(
        crossAxisSpacing = 4.dp,
        mainAxisSpacing = 8.dp,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Text(
            text = stringResource(type),
            fontWeight = FontWeight.W500,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value ?: stringResource(string.not_applicable),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
