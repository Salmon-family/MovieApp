package com.thechance.repository.mediaDataSource.series

import javax.inject.Inject
import com.thechance.repository.mediaDataSource.BasePagingSource
import com.thechance.repository.remote.response.TVShowsDTO
import com.thechance.repository.remote.service.MovieService


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