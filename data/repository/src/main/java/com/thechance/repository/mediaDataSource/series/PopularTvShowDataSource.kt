package com.thechance.repository.mediaDataSource.series

import androidx.paging.PagingState
import com.thechance.repository.mediaDataSource.BasePagingSource
import com.thechance.repository.remote.response.TVShowsDTO
import com.thechance.repository.remote.service.MovieService
import javax.inject.Inject


class PopularTvShowDataSource @Inject constructor(
    private val service: MovieService,
) : BasePagingSource<TVShowsDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShowsDTO> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getPopularTvShow(page = pageNumber)

            LoadResult.Page(
                data = response.body()?.items?: emptyList(),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TVShowsDTO>): Int? {
        return state.anchorPosition
    }
}