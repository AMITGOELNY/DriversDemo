package com.goel.riderapp.util

/**
 * Generic UI State Sealed Interface
 *
 * Captures UI states for [Loading], [Loaded], [Error], and [Empty]
 */
sealed interface UIState<out T> {
    object Loading : UIState<Nothing>
    data class Loaded<T>(val data: T) : UIState<T>
    object Error : UIState<Nothing>
    object Empty : UIState<Nothing>

    val isLoadingState: Boolean get() = this is Loading
    val isEmpty: Boolean get() = this is Empty
}
