package com.karrar.movieapp.data.repository.mediaDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class MediaDataSource<T : Any> : PagingSource<Int, T>() {

    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T>

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }
}

