package com.karrar.movieapp.ui

sealed class UIState<out T> {

    data class Success<T>(var data: T?) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
    object Loading : UIState<Nothing>()

    fun toData(): T? = if (this is Success) data else null
}