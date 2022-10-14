package com.karrar.movieapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karrar.movieapp.domain.enums.AllMediaType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@AssistedFactory
interface AllMediaFactory {
    fun create(actorID: Int, type: AllMediaType): AllMediaDataSource
}

class AllMediaDataSource @AssistedInject constructor(
    private val repository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    @Assisted private val type: AllMediaType,
    @Assisted private val actorID: Int
) : PagingSource<Int, Media>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {

        val pageNumber = params.key ?: 1

        return try {
            val response = when (type) {
                AllMediaType.TRENDING -> repository.getTrendingMovies(pageNumber)
                AllMediaType.UPCOMING -> repository.getUpcomingMovies(pageNumber)
                AllMediaType.NOW_STREAMING -> repository.getNowPlayingMovies(pageNumber)
                AllMediaType.AIRING_TODAY -> seriesRepository.getAiringToday(pageNumber)
                AllMediaType.POPULAR -> seriesRepository.getPopularTvShow(pageNumber)
                AllMediaType.TOP_RATED -> seriesRepository.getTopRatedTvShow(pageNumber)
                AllMediaType.MYSTERY -> {
                    repository.getMovieListByGenreID(Constants.MYSTERY_ID, pageNumber)
                }
                AllMediaType.ADVENTURE -> {
                    repository.getMovieListByGenreID(Constants.ADVENTURE_ID, pageNumber)
                }
                AllMediaType.ON_THE_AIR -> {
                    seriesRepository.getOnTheAir(pageNumber)
                }
                AllMediaType.NON -> {
                    if (pageNumber == 1) {
                        repository.getActorMovies(actorID)
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
