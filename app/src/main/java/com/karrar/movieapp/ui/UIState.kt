package com.karrar.movieapp.ui

sealed class UIState<out T> {
    data class Success<T>(var data: T?) : UIState<T>()
    object Error : UIState<Nothing>()
    object Loading : UIState<Nothing>()

    fun toData(): T? = if (this is Success) data else null
}