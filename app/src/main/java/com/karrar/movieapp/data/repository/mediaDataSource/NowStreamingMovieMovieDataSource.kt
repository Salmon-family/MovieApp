package com.karrar.movieapp.data.repository.mediaDataSource

import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.movie.MovieMapper
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject
import com.karrar.movieapp.data.remote.service.MovieService

class NowStreamingMovieMovieDataSource @Inject constructor(
    private val service: MovieService,
    private val mapper: MovieMapper
) : MediaDataSource<Media>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getNowPlayingMovies(page = pageNumber)

            LoadResult.Page(
                data = ListMapper(mapper).mapList(response.body()?.items),
                prevKey = null,
                nextKey = response.body()?.page?.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}