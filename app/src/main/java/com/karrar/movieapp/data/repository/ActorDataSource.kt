package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.data.remote.service.MovieService
import javax.inject.Inject


class ActorDataSource @Inject constructor(
    private val service: MovieService,
) : BasePagingSource<ActorDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ActorDto> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getTrendingActors(page = pageNumber)

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