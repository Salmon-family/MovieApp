package com.karrar.movieapp.domain

sealed class CategoryType<out T> {

    data class MovieFoo<T>(var data: T?) : CategoryType<T>()
    data class TvFoo<T>(var data: T) : CategoryType<T>()

    fun toMovieData(): T? = if (this is MovieFoo) data else null
    fun toTvData(): T? = if (this is TvFoo) data else null

}