package com.nntsl.musicbrainz.feature.artists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nntsl.musicbrainz.core.designsystem.component.MusicbrainzLoadingWheel
import com.nntsl.musicbrainz.core.designsystem.icon.MusicbrainzIcons
import com.nntsl.musicbrainz.feature.artists.R.string
import com.nntsl.musicbrainz.feature.artists.model.ArtistItem

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    navigateToArtist: (String) -> Unit,
    viewModel: ArtistsViewModel = hiltViewModel()
) {

    val uiState by viewModel.artistsUiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        navigateToArtist = navigateToArtist,
        uiState = uiState,
        searchArtist = viewModel::searchArtist
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToArtist: (String) -> Unit,
    uiState: ArtistsUiState,
    searchArtist: (String) -> Unit
) {
    Scaffold(
        topBar = { SearchAppBar(searchArtist) },
        modifier = Modifier.padding(top = 16.dp)
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (uiState) {
                ArtistsUiState.Loading ->
                    MusicbrainzLoadingWheel(
                        modifier = modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter),
                        contentDesc = stringResource(string.loading)
                    )
                is ArtistsUiState.Success -> ArtistsList(
                    artists = uiState.artists,
                    navigateToArtist = navigateToArtist
                )
                ArtistsUiState.Error, ArtistsUiState.NoData -> ArtistsEmptyScreen()
            }
        }
    }
}

@Composable
private fun ArtistsEmptyScreen() {
    Text(
        text = stringResource(string.empty_header),
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.bodyMedium
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun SearchAppBar(
    searchArtist: (String) -> Unit
) {
    val (query, onQueryChanged) = remember { mutableStateOf("") }
    val (showClearIcon, onShowClearIconChanged) = rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    onShowClearIconChanged(query.isNotEmpty())

    TextField(
        value = query,
        onValueChange = { newQuery ->
            onQueryChanged(newQuery)
            if (newQuery.isNotEmpty()) {
                searchArtist(newQuery)
            }
        },
        leadingIcon = {
            Icon(
                imageVector = MusicbrainzIcons.Search,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = {
                    onQueryChanged("")
                    searchArtist("")
                }) {
                    Icon(
                        imageVector = MusicbrainzIcons.Clear,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Clear Icon"
                    )
                }
            }
        },
        maxLines = 1,
        shape = RoundedCornerShape(32.dp),
        placeholder = { Text(text = stringResource(string.hint_search_artist)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun ArtistsList(
    artists: List<ArtistItem>,
    navigateToArtist: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(top = 8.dp)
    ) {
        if (artists.isNotEmpty()) {
            items(artists) { artist ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigateToArtist(artist.id)
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = artist.name,
                            fontWeight = FontWeight.W500,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = artist.score,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (artist.disambiguation.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, top = 4.dp, end = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = artist.disambiguation,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 4.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(artist.type.stringRes),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = artist.country,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Divider(modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}
