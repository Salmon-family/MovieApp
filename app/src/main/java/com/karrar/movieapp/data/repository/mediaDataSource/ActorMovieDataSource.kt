package com.karrar.movieapp.data.repository.mediaDataSource

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.service.MovieService
import javax.inject.Inject
import kotlin.properties.Delegates

class ActorMovieDataSource @Inject constructor(
    private val service: MovieService,
) : BasePagingSource<MovieDto>() {

    private var actorID by Delegates.notNull<Int>()

    fun setMovieActorID(actor: Int) {
        actorID = actor
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {

        return try {
            val response = service.getActorMovies(actorID)
            LoadResult.Page(
                data = response.body()?.cast?: emptyList(),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}