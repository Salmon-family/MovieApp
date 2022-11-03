package com.karrar.movieapp.data.repository.mediaDataSource.movie

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.repository.mediaDataSource.BasePagingSource
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieByGenreDataSource @Inject constructor(private val service: MovieService) :
    BasePagingSource<MovieDto>() {

    private var mediaGenreID by Delegates.notNull<Int>()

    fun setGenre(genreID: Int) {
        mediaGenreID = genreID
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getMovieListByGenre(mediaGenreID, pageNumber)
            LoadResult.Page(
                data = response.body()?.items as List<MovieDto>,
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}