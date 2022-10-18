package com.karrar.movieapp.data.repository.mediaDataSource

import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.movie.MovieMapper
import com.karrar.movieapp.domain.mappers.series.TVShowMapper
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject
import kotlin.properties.Delegates

class AllMediaDataSource @Inject constructor(
    private val service: MovieService,
    private val movieMapper: MovieMapper,
    private val tvShowMapper: TVShowMapper
) : MediaDataSource() {

    private var mediaType by Delegates.notNull<Int>()

    fun setTypeMedia(type: Int) {
        mediaType = type
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1
        return try {
            val nextPage: Int?
            val items = if (mediaType == Constants.MOVIE_CATEGORIES_ID) {
                val response = service.getAllMovies(pageNumber)
                nextPage = response.body()?.page?.plus(1)
                ListMapper(movieMapper).mapList(response.body()?.items)
            } else {
                val response = service.getAllTvShows(pageNumber)
                nextPage = response.body()?.page?.plus(1)
                ListMapper(tvShowMapper).mapList(response.body()?.items)
            }

            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}