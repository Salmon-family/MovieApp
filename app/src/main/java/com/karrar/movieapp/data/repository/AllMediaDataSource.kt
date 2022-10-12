package com.karrar.movieapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class AllMediaDataSource @Inject constructor(
    private val repository: MovieRepository,
    private val seriesRepository: SeriesRepository
) : PagingSource<Int, Media>() {
    var type: MovieType = MovieType.NON
    var actorID = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val pageNumber = params.key ?: 1
        return try {

            val response = when (type) {
                MovieType.TRENDING -> repository.getTrendingMovies2(pageNumber)
                MovieType.UPCOMING -> repository.getUpcomingMovies2(pageNumber)
                MovieType.NOW_STREAMING -> repository.getNowPlayingMovies2(pageNumber)
                MovieType.MYSTERY -> {
                    repository.getMovieListByGenreID2(Constants.MYSTERY_ID, pageNumber)
                }
                MovieType.ADVENTURE -> {
                    repository.getMovieListByGenreID2(Constants.ADVENTURE_ID, pageNumber)
                }
                MovieType.ON_THE_AIR -> {
                    seriesRepository.getOnTheAir2(pageNumber)
                }
                MovieType.NON -> {
                    if (pageNumber == 1) {
                        repository.getActorMovies2(actorID)
                    } else {
                        emptyList()
                    }
                }
            }

            LoadResult.Page(
                data = response,
                prevKey = if (response.isEmpty()) null else pageNumber - 1,
                nextKey = if (response.isEmpty()) null else pageNumber + 1,
                itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
                itemsAfter = LoadResult.Page.COUNT_UNDEFINED
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition
    }
}
