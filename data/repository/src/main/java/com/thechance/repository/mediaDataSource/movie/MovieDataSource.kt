package com.thechance.repository.mediaDataSource.movie

import com.thechance.repository.mediaDataSource.BasePagingSource
import com.thechance.repository.remote.response.MovieDto
import com.thechance.repository.remote.service.MovieService
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val service: MovieService
) : BasePagingSource<MovieDto>() {

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