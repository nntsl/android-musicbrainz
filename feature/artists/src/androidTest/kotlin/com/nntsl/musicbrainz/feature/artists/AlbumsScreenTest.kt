package com.nntsl.musicbrainz.feature.artists

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import com.nntsl.musicbrainz.feature.artists.R.string

class AlbumsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private fun getString(id: Int) = composeTestRule.activity.resources.getString(id)

    @Test
    fun loadingWheelIndicator_whenScreenIsLoading_showSuccessWithoutData() {
        composeTestRule.setContent {
            AlbumsScreen(ArtistsUiState.Success(isArtistsLoading = false, isAlbumsLoading = true))
        }

        composeTestRule.onNodeWithTag("albums:loadingWheel").assertExists()
    }

    @Test
    fun artists_whenAlbumsAreLoaded_thenShowAlbums() {
        composeTestRule.setContent {
            AlbumsScreen(
                uiState = ArtistsUiState.Success(
                    artists = previewArtists,
                    albums = previewAlbums,
                    isArtistsLoading = false,
                    isAlbumsLoading = false,
                    selectedArtist = previewArtists[0],
                    isAlbumsOpened = true
                )
            )
        }

        composeTestRule.onNodeWithText(previewArtists[0].name).assertExists()

        composeTestRule.onNodeWithTag("albums:${previewArtists[0].id}:${getString(string.type)}")
            .assertExists()
        composeTestRule
            .onNodeWithTag("albums:${previewArtists[0].id}:${getString(string.type)}:${getString(previewArtists[0].type.stringRes)}")
            .assertTextEquals(getString(previewArtists[0].type.stringRes)).assertExists()

        composeTestRule.onNodeWithTag("albums:${previewArtists[0].id}:${getString(string.score)}")
            .assertExists()
        previewArtists[0].score?.let {
            composeTestRule
                .onNodeWithTag("albums:${previewArtists[0].id}:${getString(string.score)}:${previewArtists[0].score}")
                .assertTextEquals(it).assertExists()
        }

        composeTestRule.onNodeWithTag("albums:${previewArtists[0].id}:${getString(string.country)}")
            .assertExists()
        previewArtists[0].country?.let {
            composeTestRule
                .onNodeWithTag("albums:${previewArtists[0].id}:${getString(string.country)}:${previewArtists[0].country}")
                .assertTextEquals(it).assertExists()
        }

        composeTestRule.onNodeWithTag("albums:${previewArtists[0].id}:${getString(string.begin_date)}")
            .assertExists()
        previewArtists[0].beginDate?.let {
            composeTestRule
                .onNodeWithTag("albums:${previewArtists[0].id}:${getString(string.begin_date)}:${previewArtists[0].beginDate}")
                .assertTextEquals(it).assertExists()
        }

        previewArtists[0].endDate?.let {
            composeTestRule.onNodeWithTag("albums:${previewArtists[0].id}:${getString(string.end_date)}")
                .assertExists()
            composeTestRule
                .onNodeWithTag("albums:${previewArtists[0].id}:${getString(string.end_date)}:${previewArtists[0].endDate}")
                .assertTextEquals(it).assertExists()
        }

        composeTestRule.onNodeWithText(getString(string.albums), useUnmergedTree = false).assertExists()

        composeTestRule.onNodeWithTag("albums:${previewAlbums[0].id}").assertExists()
        composeTestRule.onNodeWithText(previewAlbums[0].title).assertExists()

        composeTestRule.onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.status)}")
            .assertExists()
        previewAlbums[0].status?.let {
            composeTestRule
                .onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.status)}:${it}")
                .assertTextEquals(it).assertExists()
        }

        composeTestRule.onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.tracks)}")
            .assertExists()
        previewAlbums[0].tracks?.let {
            composeTestRule
                .onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.tracks)}:${it}")
                .assertTextEquals(it).assertExists()
        }

        composeTestRule.onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.date)}")
            .assertExists()
        previewAlbums[0].date?.let {
            composeTestRule
                .onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.date)}:${it}")
                .assertTextEquals(it).assertExists()
        }

        composeTestRule.onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.country)}")
            .assertExists()
        previewAlbums[0].country?.let {
            composeTestRule
                .onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.country)}:${it}")
                .assertTextEquals(it).assertExists()
        }

        composeTestRule.onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.score)}")
            .assertExists()
        previewAlbums[0].score?.let {
            composeTestRule
                .onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.score)}:${it}")
                .assertTextEquals(it).assertExists()
        }

        composeTestRule.onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.packaging)}")
            .assertExists()
        previewAlbums[0].packaging?.let {
            composeTestRule
                .onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.packaging)}:${it}")
                .assertTextEquals(it).assertExists()
        }

        composeTestRule.onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.barcode)}")
            .assertExists()
        // barcode is null
        composeTestRule
            .onNodeWithTag("albums:${previewAlbums[0].id}:${getString(string.barcode)}:${previewAlbums[0].barcode}")
            .assertTextEquals(getString(string.not_applicable)).assertExists()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(hasTestTag("albums:${previewAlbums[1].id}"))
        composeTestRule.onNodeWithText(previewAlbums[1].title).assertExists()
    }

    @Composable
    private fun AlbumsScreen(uiState: ArtistsUiState, isExpandedScreen: Boolean = false) {
        com.nntsl.musicbrainz.feature.artists.screen.AlbumsScreen(
            uiState = uiState,
            onBackClick = {},
            isExpandedScreen = isExpandedScreen
        )
    }
}
