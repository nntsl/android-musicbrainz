package com.nntsl.musicbrainz.feature.artists

import com.nntsl.musicbrainz.core.domain.usecase.GetAlbumsUseCase
import com.nntsl.musicbrainz.core.domain.usecase.GetArtistsUseCase
import com.nntsl.musicbrainz.core.testing.repository.TestAlbumsRepository
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

    private val albumsRepository = TestAlbumsRepository()

    private val getArtistsUseCase = GetArtistsUseCase(
        artistsRepository = artistsRepository
    )

    private val getAlbumsUseCase = GetAlbumsUseCase(
        albumsRepository = albumsRepository
    )

    private lateinit var viewModel: ArtistsViewModel

    @Before
    fun setUp() {
        viewModel = ArtistsViewModel(
            getArtistsUseCase = getArtistsUseCase,
            getAlbumsUseCase = getAlbumsUseCase
        )
    }

    @Test
    fun uiState_whenInitialized_thenShowSuccessWithoutData() = runTest {
        assertEquals(
            ArtistsUiState.Success(
                isArtistsLoading = false,
                isAlbumsLoading = false
            ),
            viewModel.artistsUiState.value
        )
    }

    @Test
    fun uiState_whenSearchArtists_thenShowUpdatedArtists() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.artistsUiState.collect() }

        val query = "test"
        viewModel.searchArtists(query)
        artistsRepository.sendArtists(testInputArtists.filter { it.name == query })

        assertEquals(
            ArtistsUiState.Success(
                isArtistsLoading = false,
                isAlbumsLoading = false,
                artists = testInputArtistsItems.filter { it.name == query }
            ),
            viewModel.artistsUiState.value
        )

        collectJob1.cancel()
    }

    @Test
    fun uiState_whenSelectArtist_thenShowArtistAlbums() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.artistsUiState.collect() }

        viewModel.interactedWithArtists(testInputArtistsItems.first())
        albumsRepository.sendAlbums(testInputAlbums)

        assertEquals(
            ArtistsUiState.Success(
                isArtistsLoading = false,
                isAlbumsLoading = false,
                isAlbumsOpened = true,
                albums = testInputAlbumsItems,
                selectedArtist = testInputArtistsItems.first()
            ),
            viewModel.artistsUiState.value
        )

        collectJob1.cancel()
    }
}
