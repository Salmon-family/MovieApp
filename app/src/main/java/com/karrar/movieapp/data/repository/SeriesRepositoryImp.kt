package com.karrar.movieapp.data.repository

import androidx.paging.Pager
import com.karrar.movieapp.data.Constants
import com.karrar.movieapp.data.local.AppConfiguration
import com.karrar.movieapp.data.local.AppConfigurator
import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.daos.SeriesDao
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.series.AiringTodaySeriesEntity
import com.karrar.movieapp.data.local.database.entity.series.OnTheAirSeriesEntity
import com.karrar.movieapp.data.local.database.entity.series.TopRatedSeriesEntity
import com.karrar.movieapp.data.repository.mediaDataSource.series.SeriesDataSourceContainer
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.data.remote.response.RatedTvShowDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.repository.serchDataSource.SearchDataSourceContainer
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.MediaDataSourceContainer
import com.karrar.movieapp.domain.mappers.SeriesMapperContainer
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val seriesDao: SeriesDao,
    private val appConfiguration: AppConfiguration,
    private val seriesMapperContainer: SeriesMapperContainer,
    private val localSeriesMappersContainer: LocalSeriesMappersContainer,
    private val seriesDataSourceContainer: SeriesDataSourceContainer,
    private val mediaDataSourceContainer: MediaDataSourceContainer,
    private val searchDataSourceContainer: SearchDataSourceContainer,
    ) : BaseRepository(), SeriesRepository {

    override suspend fun getTVShowsGenreList(): List<Genre> {
        return wrap({ service.getGenreTvShowList() },
            { ListMapper(seriesMapperContainer.genreMapper).mapList(it.genres) })
    }

    override suspend fun getTVShowsGenreList2(): List<GenreDto>? {
        return service.getGenreTvShowList().body()?.genres
    }

    override suspend fun getTvShowDetails(tvShowId: Int): TvShowDetails {
        return wrap({ service.getTvShowDetails(tvShowId) }, { response ->
            seriesMapperContainer.tvShowDetailsMapper.map(response)
        })
    }

    override suspend fun getTvShowCast(tvShowId: Int): List<Actor> {
        return wrap({ service.getTvShowCast(tvShowId) },
            { ListMapper(seriesMapperContainer.actorMapper).mapList(it.cast) })
    }

    override suspend fun getTvShowReviews(tvShowId: Int): List<Review> {
        return wrap({ service.getTvShowReviews(tvShowId) },
            { ListMapper(seriesMapperContainer.reviewMapper).mapList(it.items) })
    }

    override suspend fun setRating(tvShowId: Int, value: Float, sessionId: String): RatingDto {
        return wrap({ service.postTvShowRating(tvShowId, value, sessionId) }, { it })
    }

    override suspend fun getRatedTvShow(accountId: Int, sessionId: String)
            : List<RatedTvShowDto>? {
        return service.getRatedTvShow(accountId, sessionId).body()?.items
    }

    override suspend fun getSeasonDetails(tvShowId: Int, seasonId: Int): Season {
        return wrap({ service.getSeasonDetails(tvShowId, seasonId) },
            { seriesMapperContainer.seasonMapper.map(it) })
    }

    override suspend fun getTvShowTrailer(tvShowId: Int): Trailer {
        return wrap({ service.getTvShowTrailer(tvShowId) },
            { seriesMapperContainer.trailerMapper.map(it) })
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
        refreshOneTimePerDay(appConfiguration.getRequestDate(Constants.AIRING_TODAY_SERIES_REQUEST_DATE_KEY),::refreshAiringToday)
        return seriesDao.getAiringTodaySeries()
    }

    override suspend fun getOnTheAir(): Flow<List<OnTheAirSeriesEntity>> {
        refreshOneTimePerDay(appConfiguration.getRequestDate(Constants.ON_THE_AIR_SERIES_REQUEST_DATE_KEY),::refreshOnTheAir)
        return seriesDao.getOnTheAirSeries()
    }

    override suspend fun getTopRatedTvShow(): Flow<List<TopRatedSeriesEntity>> {
        refreshOneTimePerDay(appConfiguration.getRequestDate(Constants.TOP_RATED_SERIES_REQUEST_DATE_KEY),::refreshTopRatedTvShow)
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
        return Pager(config = config, pagingSourceFactory = {dataSource})
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
                appConfiguration.saveRequestDate(Constants.AIRING_TODAY_SERIES_REQUEST_DATE_KEY,currentDate.time)
            },
        )
    }

     private suspend fun refreshOnTheAir(currentDate : Date) {
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
                appConfiguration.saveRequestDate(Constants.ON_THE_AIR_SERIES_REQUEST_DATE_KEY,currentDate.time)
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
            appConfiguration.saveRequestDate(Constants.TOP_RATED_SERIES_REQUEST_DATE_KEY,currentDate.time)
        } catch (_: Throwable) {

        }
    }

}