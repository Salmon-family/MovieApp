package com.karrar.movieapp.data.mediaDataSource.movie

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.mediaDataSource.BasePagingSource
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieGenreShowDataSource @Inject constructor(
    private val service: MovieService,
) : BasePagingSource<MovieDto>() {

    private var mediaGenreID by Delegates.notNull<Int>()
    private var mediaType by Delegates.notNull<Int>()


    fun setGenre(genreID: Int, type: Int) {
        mediaGenreID = genreID
        mediaType = type
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val pageNumber = params.key ?: 1

        return try {
            val nextPage: Int?
            val list = if (mediaType == Constants.MOVIE_CATEGORIES_ID) {
                val response = service.getMovieListByGenre(mediaGenreID, pageNumber)
                nextPage = response.body()?.page?.plus(1)
                response.body()?.items ?: emptyList<MovieDto>()
            } else {
                val response = service.getTvListByGenre(mediaGenreID, pageNumber)
                nextPage = response.body()?.page?.plus(1)
                response.body()?.items ?: emptyList<MovieDto>()
            }


            LoadResult.Page(
                data = list as List<MovieDto>,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}