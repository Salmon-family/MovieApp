package com.karrar.movieapp.domain

sealed class CategoryType<out T> {

    data class Movies<T>(var data: T?) : CategoryType<T>()
    data class TvShows<T>(var data: T) : CategoryType<T>()

    fun toMovieData(): T? = if (this is Movies) data else null
    fun toTvData(): T? = if (this is TvShows) data else null

}