package com.karrar.movieapp.data.repository.mediaDataSource

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.service.MovieService
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val service: MovieService
) : MediaDataSource<MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getAllMovies(pageNumber)

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