package com.nntsl.musicbrainz.core.common.decoder.result

import kotlinx.coroutines.flow.*

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable? = null) : Result<Nothing>
    object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(resume: Boolean = false): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .onStart { if (!resume) emit(Result.Loading) }
        .catch {
            emit(Result.Error(it))
        }
}