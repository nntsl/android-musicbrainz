package com.nntsl.musicbrainz.feature.artists

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.nntsl.musicbrainz.feature.artists.screen.ArtistsWithAlbumsScreen
import org.junit.Rule
import org.junit.Test

class ArtistsWithAlbumsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingWheelIndicator_whenScreenIsLoading_showSuccessWithoutData() {
        composeTestRule.setContent {
            ArtistsWithAlbumsScreen(ArtistsUiState.Success(isArtistsLoading = true, isAlbumsLoading = false))
        }

        composeTestRule.onNodeWithTag("artists:loadingWheel").assertExists()
    }

    @Test
    fun artists_whenSearchArtist_thenHidePreviousAlbums() {
        composeTestRule.setContent {
            ArtistsWithAlbumsScreen(
                uiState = ArtistsUiState.Success(
                    artists = previewArtists,
                    albums = previewAlbums,
                    isArtistsLoading = false,
                    isAlbumsLoading = false,
                    selectedArtist = previewArtists.first()
                ),
                albumsLazyListStates = mapOf(),
                searchArtist = {},
                navigateToArtist = {}
            )
        }
    }

    @Composable
    private fun ArtistsWithAlbumsScreen(uiState: ArtistsUiState) {
        ArtistsWithAlbumsScreen(
            uiState = uiState,
            navigateToArtist = {},
            searchArtist = {},
            albumsLazyListStates = mapOf()
        )
    }
}
