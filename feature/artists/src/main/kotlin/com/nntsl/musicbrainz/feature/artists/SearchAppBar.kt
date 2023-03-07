package com.nntsl.musicbrainz.feature.artists

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nntsl.musicbrainz.core.designsystem.icon.MusicbrainzIcons

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun SearchAppBar(
    modifier: Modifier = Modifier,
    searchArtist: (String) -> Unit,
    currentQuery: String = ""
) {
    val (query, onQueryChanged) = remember { mutableStateOf(currentQuery) }
    val (showClearIcon, onShowClearIconChanged) = rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    onShowClearIconChanged(query.isNotEmpty())

    TextField(
        value = query,
        onValueChange = { newQuery ->
            onQueryChanged(newQuery)
            searchArtist(newQuery)
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
        placeholder = { Text(text = stringResource(R.string.hint_search_artist)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}
