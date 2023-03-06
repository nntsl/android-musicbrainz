package com.nntsl.musicbrainz.feature.artists

import com.nntsl.musicbrainz.core.domain.usecase.GetArtistsUseCase
import com.nntsl.musicbrainz.core.testing.repository.TestArtistsRepository
import com.nntsl.musicbrainz.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.collect
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class ArtistsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val artistsRepository = TestArtistsRepository()

    private val getArtistsUseCase = GetArtistsUseCase(
        artistsRepository = artistsRepository
    )

    private lateinit var viewModel: ArtistsViewModel

    @Before
    fun setUp() {
        viewModel = ArtistsViewModel(
            getArtistsUseCase = getArtistsUseCase
        )
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        assertEquals(
            ArtistsUiState.Loading,
            viewModel.artistsUiState.value
        )
    }

    @Test
    fun uiState_whenArtistsLoaded_thenShowSuccess() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.artistsUiState.collect() }

        artistsRepository.sendArtists(testInputArtists)

        assertEquals(
            ArtistsUiState.Success(testInputArtistsItems),
            viewModel.artistsUiState.value
        )

        collectJob1.cancel()
    }

    @Test
    fun uiState_whenSearchArtists_thenShowUpdatedArtists() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.artistsUiState.collect() }

        artistsRepository.sendArtists(testInputArtists.filter { it.name == "test" })

        assertEquals(
            ArtistsUiState.Success(
                artists = testInputArtistsItems.filter { it.name == "test" }
            ),
            viewModel.artistsUiState.value
        )

        collectJob1.cancel()
    }
}
