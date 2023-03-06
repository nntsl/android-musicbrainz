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


    private fun getString(id: Int) = composeTestRule.activity.resources.getString(id)

    private fun getString(id: Int, vararg format: Any) = composeTestRule.activity.resources.getString(id, format)


    @Test
    fun loadingWheelIndicator_whenScreenIsLoading_showLoading() {
        composeTestRule.setContent {
            ArtistsScreen(ArtistsUiState.Loading)
        }

        composeTestRule.onNodeWithTag("artists:loadingWheel").assertExists()
    }

    @Test
    fun artists_whenDataIsEmpty_thenShowEmptyScreen() {
        composeTestRule.setContent {
            ArtistsScreen(ArtistsUiState.NoData)
        }
    }

    @Test
    fun exchangeRates_whenLoaded_thenIsShown() {
        composeTestRule.setContent {
            ArtistsScreen(
                uiState = ArtistsUiState.Success(
                    artists = previewArtists
                )
            )
        }

        composeTestRule.onNodeWithTag("artists:${previewArtists[0].id}")
            .assertExists().assertHasClickAction()
        composeTestRule.onNodeWithText(previewArtists[0].name).assertExists()
        composeTestRule.onNodeWithText(previewArtists[0].score).assertExists()
        composeTestRule.onNodeWithText(getString(previewArtists[0].type.stringRes)).assertExists()
        composeTestRule.onNodeWithText(previewArtists[0].country).assertExists()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(hasText(previewArtists[1].name))
        composeTestRule.onNodeWithTag("artists:${previewArtists[1].id}")
            .assertExists().assertHasClickAction()
        composeTestRule.onNodeWithText(previewArtists[1].name).assertExists()
        composeTestRule.onNodeWithText(previewArtists[1].score).assertExists()
        composeTestRule.onNodeWithText(getString(previewArtists[1].type.stringRes)).assertExists()
        composeTestRule.onNodeWithText(previewArtists[1].country).assertExists()
    }

    @Composable
    private fun ArtistsScreen(uiState: ArtistsUiState) {
        ArtistsScreen(
            uiState = uiState,
            navigateToArtist = {},
            searchArtist = {}
        )
    }
}