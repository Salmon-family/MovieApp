package com.karrar.movieapp.data.repository.mediaDataSource

import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.movie.MovieMapper
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject
import kotlin.properties.Delegates
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.models.Actor

class ActorMovieDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: MovieMapper
) : MediaDataSource<Media>() {

    private var actorID by Delegates.notNull<Int>()

    fun setMovieActorID(actor: Int) {
        actorID = actor
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {

        return try {
            val response = service.getActorMovies(actorID)
            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.cast),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}