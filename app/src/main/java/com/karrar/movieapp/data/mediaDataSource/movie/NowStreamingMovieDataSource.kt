package com.karrar.movieapp.data.mediaDataSource.movie

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.mediaDataSource.BasePagingSource
import javax.inject.Inject

class NowStreamingMovieDataSource @Inject constructor(
    private val service: MovieService,
) : BasePagingSource<MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getNowPlayingMovies(page = pageNumber)

            LoadResult.Page(
                data = response.body()?.items?: emptyList(),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}