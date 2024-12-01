package com.app.android_test.core.app

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {

    object Loading : Result<Nothing>()
//    data class Success<out T : Any>(val data: T) : Result<T>()

    data class Success<out T : Any>(val data: T) : Result<T>() // Represents a success state

    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Loading -> "Loading..."
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
