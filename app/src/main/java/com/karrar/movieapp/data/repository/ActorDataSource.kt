package com.karrar.movieapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karrar.movieapp.domain.models.Actor
import javax.inject.Inject


class ActorDataSource @Inject constructor(
    private val repository: MovieRepository
) : PagingSource<Int, Actor>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Actor> {
        val pageNumber = params.key ?: 1
        return try {
            val response = repository.getTrendingActors2(pageNumber)

            LoadResult.Page(
                data = response,
                prevKey = if (response.isEmpty()) null else pageNumber - 1,
                nextKey = if (response.isEmpty()) null else pageNumber + 1,
                itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
                itemsAfter = LoadResult.Page.COUNT_UNDEFINED
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Actor>): Int? {
        return state.anchorPosition
    }
}