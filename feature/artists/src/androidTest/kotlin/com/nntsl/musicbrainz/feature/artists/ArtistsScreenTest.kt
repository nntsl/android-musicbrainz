package com.nntsl.musicbrainz.feature.artists

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class ArtistsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingWheelIndicator_whenScreenIsLoading_showSuccessWithoutData() {
        composeTestRule.setContent {
            ArtistsScreen(ArtistsUiState.Success(isArtistsLoading = true, isAlbumsLoading = false))
        }

        composeTestRule.onNodeWithTag("artists:loadingWheel").assertExists()
    }

    @Test
    fun artists_whenDataIsEmpty_thenShowEmptyScreen() {
        composeTestRule.setContent {
            ArtistsScreen(ArtistsUiState.NoData(isArtistsLoading = false, isAlbumsLoading = false))
        }
    }

    @Test
    fun artists_whenArtistsAreLoaded_thenShowArtists() {
        composeTestRule.setContent {
            ArtistsScreen(
                uiState = ArtistsUiState.Success(
                    artists = previewArtists,
                    isArtistsLoading = false,
                    isAlbumsLoading = false
                )
            )
        }

        composeTestRule.onNodeWithTag("artists:${previewArtists[0].id}")
            .assertExists().assertHasClickAction()
        composeTestRule.onNodeWithText(previewArtists[0].name).assertExists()
        previewArtists[0].score?.let { composeTestRule.onNodeWithText(it).assertExists() }
        previewArtists[0].country?.let { composeTestRule.onNodeWithText(it).assertExists() }

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(hasText(previewArtists[1].name))
        composeTestRule.onNodeWithTag("artists:${previewArtists[1].id}")
            .assertExists().assertHasClickAction()
        composeTestRule.onNodeWithText(previewArtists[1].name).assertExists()
        previewArtists[1].score?.let { composeTestRule.onNodeWithText(it).assertExists() }
        previewArtists[1].country?.let { composeTestRule.onNodeWithText(it).assertExists() }
    }

    @Composable
    private fun ArtistsScreen(uiState: ArtistsUiState) {
        com.nntsl.musicbrainz.feature.artists.screen.ArtistsScreen(
            uiState = uiState,
            navigateToArtist = {},
            searchArtist = {}
        )
    }
}
