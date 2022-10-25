package com.karrar.movieapp.domain.allMedia

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.karrar.movieapp.domain.enums.AllMediaType
import com.karrar.movieapp.domain.mappers.MediaDataSourceContainer
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class GetMediaByTypeUseCase @Inject constructor(
    private val mediaDataSourceContainer: MediaDataSourceContainer,
) {
    private val config = PagingConfig(pageSize = 100, prefetchDistance = 5, enablePlaceholders = false)

    operator fun invoke(type:AllMediaType) : Pager<Int, Media> {
        val pagingSourceFactory = when (type) {
            AllMediaType.ON_THE_AIR, AllMediaType.LATEST -> mediaDataSourceContainer.onTheAirTvShowDataSource
            AllMediaType.AIRING_TODAY -> mediaDataSourceContainer.airingTodayTvShowDataSource
            AllMediaType.POPULAR -> mediaDataSourceContainer.popularTvShowDataSource
            AllMediaType.TOP_RATED -> mediaDataSourceContainer.topRatedTvShowDataSource
            AllMediaType.TRENDING -> mediaDataSourceContainer.trendingMovieDataSource
            AllMediaType.NOW_STREAMING -> mediaDataSourceContainer.nowStreamingMovieMovieDataSource
            AllMediaType.UPCOMING -> mediaDataSourceContainer.upcomingMovieMovieDataSource
            AllMediaType.MYSTERY -> {
                val dataSource = mediaDataSourceContainer.movieGenreShowDataSource
                dataSource.setGenre(Constants.MYSTERY_ID, Constants.MOVIE_CATEGORIES_ID)
                dataSource
            }
            AllMediaType.NON,
            AllMediaType.ADVENTURE -> {
                val dataSource = mediaDataSourceContainer.movieGenreShowDataSource
                dataSource.setGenre(Constants.ADVENTURE_ID, Constants.MOVIE_CATEGORIES_ID)
                dataSource
            }

        }
        return Pager(config = config, pagingSourceFactory = { pagingSourceFactory })
    }
}