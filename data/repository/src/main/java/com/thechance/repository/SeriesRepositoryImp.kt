package com.thechance.repository

import androidx.paging.Pager
import com.thechance.local.database.daos.MovieDao
import com.thechance.local.database.daos.SeriesDao
import com.thechance.local.database.entity.WatchHistoryEntity
import com.thechance.local.database.entity.series.AiringTodaySeriesEntity
import com.thechance.local.database.entity.series.OnTheAirSeriesEntity
import com.thechance.local.database.entity.series.TopRatedSeriesEntity
import com.thechance.remote.response.CreditsDto
import com.thechance.remote.response.RatedTvShowDto
import com.thechance.remote.response.TVShowsDTO
import com.thechance.remote.response.episode.EpisodeDto
import com.thechance.remote.response.genre.GenreDto
import com.thechance.remote.response.movie.RatingDto
import com.thechance.remote.response.review.ReviewsDto
import com.thechance.remote.response.trailerVideosDto.TrailerDto
import com.thechance.remote.response.tvShow.TvShowDetailsDto
import com.thechance.remote.service.MovieService
import com.thechance.repository.configuration.AppConfiguration
import com.thechance.repository.mapper.series.LocalSeriesMappersContainer
import com.thechance.repository.mediaDataSource.series.SeriesDataSourceContainer
import com.thechance.repository.serchDataSource.SearchDataSourceContainer
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val seriesDao: SeriesDao,
    private val appConfiguration: AppConfiguration,
    private val localSeriesMappersContainer: LocalSeriesMappersContainer,
    private val seriesDataSourceContainer: SeriesDataSourceContainer,
    private val mediaDataSourceContainer: MediaDataSourceContainer,
    private val searchDataSourceContainer: SearchDataSourceContainer,
) : BaseRepository(), SeriesRepository {

    override suspend fun getTVShowsGenreList(): List<GenreDto>? {
        return service.getGenreTvShowList().body()?.genres
    }

    override suspend fun getTvShowTrailer(tvShowId: Int): TrailerDto? {
        return service.getTvShowTrailer(tvShowId).body()
    }

    override suspend fun insertTvShow(tvShow: WatchHistoryEntity) {
        return movieDao.insert(tvShow)
    }

    override suspend fun getAllTVShows(): Pager<Int, TVShowsDTO> {
        return Pager(
            config = config,
            pagingSourceFactory = { mediaDataSourceContainer.tvShowDataSource })
    }

    override suspend fun getTVShowByGenre(genreID: Int): Pager<Int, TVShowsDTO> {
        return Pager(
            config = config,
            pagingSourceFactory = {
                val dataSource = mediaDataSourceContainer.tvShowsByGenreDataSource
                dataSource.setGenre(genreID)
                dataSource
            }
        )
    }

    override suspend fun getAiringToday(): Flow<List<AiringTodaySeriesEntity>> {
        refreshOneTimePerDay(
            appConfiguration.getRequestDate(Constants.AIRING_TODAY_SERIES_REQUEST_DATE_KEY),
            ::refreshAiringToday
        )
        return seriesDao.getAiringTodaySeries()
    }

    override suspend fun getOnTheAir(): Flow<List<OnTheAirSeriesEntity>> {
        refreshOneTimePerDay(
            appConfiguration.getRequestDate(Constants.ON_THE_AIR_SERIES_REQUEST_DATE_KEY),
            ::refreshOnTheAir
        )
        return seriesDao.getOnTheAirSeries()
    }

    override suspend fun getTopRatedTvShow(): Flow<List<TopRatedSeriesEntity>> {
        refreshOneTimePerDay(
            appConfiguration.getRequestDate(Constants.TOP_RATED_SERIES_REQUEST_DATE_KEY),
            ::refreshTopRatedTvShow
        )
        return seriesDao.getTopRatedSeries()
    }


    override fun getAiringTodayTvShowPager(): Pager<Int, TVShowsDTO> {
        return Pager(
            config = config,
            pagingSourceFactory = { seriesDataSourceContainer.airingTodayTvShowDataSource })
    }

    override fun getOnTheAirTvShowPager(): Pager<Int, TVShowsDTO> {
        return Pager(
            config = config,
            pagingSourceFactory = { seriesDataSourceContainer.onTheAirTvShowDataSource })
    }

    override fun getTopRatedTvShowPager(): Pager<Int, TVShowsDTO> {
        return Pager(
            config = config,
            pagingSourceFactory = { seriesDataSourceContainer.topRatedTvShowDataSource })
    }

    override fun getPopularTvShowPager(): Pager<Int, TVShowsDTO> {
        return Pager(
            config = config,
            pagingSourceFactory = { seriesDataSourceContainer.popularTvShowDataSource })
    }

    override suspend fun searchForSeriesPager(query: String): Pager<Int, TVShowsDTO> {
        val dataSource = searchDataSourceContainer.seriesSearchDataSource
        dataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = { dataSource })
    }


    private suspend fun refreshAiringToday(currentDate: Date) {
        refreshWrapper(
            { service.getAiringToday() },
            { list ->
                list?.map {
                    localSeriesMappersContainer.airingTodaySeriesMapper.map(it)
                }
            },
            {
                seriesDao.deleteAllAiringTodaySeries()
                seriesDao.insertAiringTodaySeries(it)
                appConfiguration.saveRequestDate(
                    Constants.AIRING_TODAY_SERIES_REQUEST_DATE_KEY,
                    currentDate.time
                )
            },
        )
    }

    private suspend fun refreshOnTheAir(currentDate: Date) {
        refreshWrapper(
            { service.getOnTheAir() },
            { list ->
                list?.map {
                    localSeriesMappersContainer.onTheAirSeriesMapper.map(it)
                }
            },
            {
                seriesDao.deleteAllOnTheAirSeries()
                seriesDao.insertOnTheAirSeries(it)
                appConfiguration.saveRequestDate(
                    Constants.ON_THE_AIR_SERIES_REQUEST_DATE_KEY,
                    currentDate.time
                )
            },
        )
    }

    private suspend fun refreshTopRatedTvShow(currentDate: Date) {
        try {
            val items = mutableListOf<TopRatedSeriesEntity>()
            service.getTopRatedTvShow().body()?.items?.first()?.let {
                items.add(localSeriesMappersContainer.topRatedSeriesMapper.map(it))
            }
            service.getPopularTvShow().body()?.items?.first()?.let {
                items.add(localSeriesMappersContainer.topRatedSeriesMapper.map(it))
            }
            service.getAiringToday().body()?.items?.first()?.let {
                items.add(localSeriesMappersContainer.topRatedSeriesMapper.map(it))
            }
            seriesDao.deleteAllTopRatedSeries()
            seriesDao.insertTopRatedSeries(items)
            appConfiguration.saveRequestDate(
                Constants.TOP_RATED_SERIES_REQUEST_DATE_KEY,
                currentDate.time
            )
        } catch (_: Throwable) {

        }
    }

    override suspend fun getTvShowDetails(tvShowId: Int): TvShowDetailsDto? {
        return service.getTvShowDetails(tvShowId).body()
    }

    override suspend fun getTvShowCast(tvShowId: Int): CreditsDto? {
        return service.getTvShowCast(tvShowId).body()
    }

    override suspend fun getTvShowReviews(tvShowId: Int): List<ReviewsDto>? {
        return service.getTvShowReviews(tvShowId).body()?.items
    }

    override suspend fun setRating(tvShowId: Int, value: Float): RatingDto? {
        return service.postTvShowRating(tvShowId, value).body()
    }

    override suspend fun getRatedTvShow(): List<RatedTvShowDto>? {
        return service.getRatedTvShow().body()?.items
    }

    override suspend fun getSeasonDetails(tvShowId: Int): List<EpisodeDto>? {
        return service.getSeasonDetails(tvShowId).body()?.episodes
    }
}