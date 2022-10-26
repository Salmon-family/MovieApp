package com.karrar.movieapp.data.mediaDataSource.series

import com.karrar.movieapp.data.remote.response.TVShowsDTO
import javax.inject.Inject
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.mediaDataSource.BasePagingSource


class TopRatedTvShowDataSource @Inject constructor(
    private val service: MovieService,
) : BasePagingSource<TVShowsDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShowsDTO> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getTopRatedTvShow(page = pageNumber)

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