package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.daos.SeriesDao
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.series.TopRatedSeriesEntity
import com.karrar.movieapp.data.local.mappers.series.LocalSeriesMappersContainer
import com.karrar.movieapp.data.remote.response.BaseListResponse
import com.karrar.movieapp.data.remote.response.CreditsDto
import com.karrar.movieapp.data.remote.response.RatedTvShowDto
import com.karrar.movieapp.data.remote.response.SeasonDto
import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.response.review.ReviewsDto
import com.karrar.movieapp.data.remote.response.tvShow.TvShowDetailsDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.SeriesMapperContainer
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val seriesDao: SeriesDao,
    private val seriesMapperContainer: SeriesMapperContainer,
    private val localSeriesMappersContainer: LocalSeriesMappersContainer,
) : BaseRepository(), SeriesRepository {

    override suspend fun getTVShowsGenreList(): List<Genre> {
        return wrap({ service.getGenreTvShowList() },
            { ListMapper(seriesMapperContainer.genreMapper).mapList(it.genres) })
    }

    override suspend fun getOnTheAir(page: Int): List<Media> {
        return wrap({ service.getOnTheAir(page) },
            { ListMapper(seriesMapperContainer.mediaMapper).mapList(it.items) })
    }

    override suspend fun getAiringToday(page: Int): List<Media> {
        return wrap({ service.getAiringToday(page) },
            { ListMapper(seriesMapperContainer.mediaMapper).mapList(it.items) })
    }


    override suspend fun getTopRatedTvShow(page: Int): List<Media> {
        return wrap({ service.getTopRatedTvShow(page) },
            { ListMapper(seriesMapperContainer.mediaMapper).mapList(it.items) })
    }

    override suspend fun getPopularTvShow(page: Int): List<Media> {
        return wrap({ service.getPopularTvShow(page) },
            { ListMapper(seriesMapperContainer.mediaMapper).mapList(it.items) })
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

    override suspend fun setRating(tvShowId: Int, value: Float, sessionId: String): RatingDto? {
        return service.postTvShowRating(tvShowId, value, sessionId).body()
    }

    override suspend fun getRatedTvShow(accountId: Int, sessionId: String): List<RatedTvShowDto>? {
        return service.getRatedTvShow(accountId, sessionId).body()?.items
    }

    override suspend fun getSeasonDetails(tvShowId: Int, seasonId: Int): SeasonDto? {
        return service.getSeasonDetails(tvShowId, seasonId).body()
    }

    override suspend fun getTvShowTrailer(tvShowId: Int): Trailer {
        return wrap({ service.getTvShowTrailer(tvShowId) },
            { seriesMapperContainer.trailerMapper.map(it) })
    }

    override suspend fun insertTvShow(tvShow: WatchHistoryEntity) {
        return movieDao.insert(tvShow)
    }

    /**
     * Caching
     * */

    override fun getAiringToday(): Flow<List<Media>> {
        return seriesDao.getAiringTodaySeries().map { list ->
            list.map { seriesMapperContainer.airingTodaySeriesMapper.map(it) }
        }
    }

    override fun getOnTheAir(): Flow<List<Media>> {
        return seriesDao.getOnTheAirSeries().map { list ->
            list.map { seriesMapperContainer.onTheAirSeriesMapper.map(it) }
        }
    }

    override fun getTopRatedTvShow(): Flow<List<Media>> {
        return seriesDao.getTopRatedSeries().map { list ->
            list.map { seriesMapperContainer.topRatedSeriesMapper.map(it) }
        }
    }


    override suspend fun refreshAiringToday() {
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
            },
        )
    }

    override suspend fun refreshOnTheAir() {
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
            },
        )
    }

    override suspend fun refreshTopRatedTvShow() {
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
        } catch (throwable: Throwable) {

        }
    }

}