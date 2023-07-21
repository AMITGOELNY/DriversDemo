package com.goel.riderapp.util

/**
 * Generic Network Result Sealed Interface
 *
 * Captures network states for [Loading], [Success], and [Error]
 */
sealed interface NetworkResult<out T, out E> {
    object Loading : NetworkResult<Nothing, Nothing>
    data class Success<T>(val data: T) : NetworkResult<T, Nothing>
    data class Error<E>(val error: E) : NetworkResult<Nothing, E>
}
