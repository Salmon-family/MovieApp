package com.karrar.movieapp.data.repository.mediaDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.models.Media

abstract class MediaDataSource : PagingSource<Int, Media>() {

    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media>

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition
    }
}

