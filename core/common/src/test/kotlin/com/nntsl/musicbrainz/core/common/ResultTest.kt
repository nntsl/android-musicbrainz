package com.nntsl.musicbrainz.core.common

import app.cash.turbine.test
import com.nntsl.musicbrainz.core.common.decoder.result.Result
import com.nntsl.musicbrainz.core.common.decoder.result.asResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ResultTest {

    @Test
    fun result_whenException_thenCatchError() = runTest {
        flow {
            emit(1)
            throw Exception("Test Done")
        }
            .asResult()
            .test {
                assertEquals(Result.Loading, awaitItem())
                assertEquals(Result.Success(1), awaitItem())

                when (val errorResult = awaitItem()) {
                    is Result.Error -> assertEquals(
                        "Test Done",
                        errorResult.exception?.message
                    )
                    Result.Loading,
                    is Result.Success -> throw IllegalStateException("Should emit Error Result")
                }
                awaitComplete()
            }
    }
}
