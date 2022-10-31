package com.thechance.repository

import androidx.paging.Pager
import com.thechance.repository.local.database.daos.MovieDao
import com.thechance.repository.local.database.daos.SeriesDao
import com.thechance.repository.local.database.entity.WatchHistoryEntity
import com.thechance.repository.local.database.entity.series.AiringTodaySeriesEntity
import com.thechance.repository.local.database.entity.series.OnTheAirSeriesEntity
import com.thechance.repository.local.database.entity.series.TopRatedSeriesEntity
import com.thechance.repository.mediaDataSource.series.SeriesDataSourceContainer
import com.thechance.repository.remote.response.CreditsDto
import com.thechance.repository.remote.response.RatedTvShowDto
import com.thechance.repository.remote.response.SeasonDto
import com.thechance.repository.remote.response.TVShowsDTO
import com.thechance.repository.remote.response.actor.ActorDto
import com.thechance.repository.remote.response.genre.GenreDto
import com.thechance.repository.remote.response.movie.RatingDto
import com.thechance.repository.remote.response.review.ReviewsDto
import com.thechance.repository.remote.response.trailerVideosDto.TrailerDto
import com.thechance.repository.remote.response.tvShow.TvShowDetailsDto
import com.thechance.repository.remote.service.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val seriesDao: SeriesDao,
    private val seriesDataSourceContainer: SeriesDataSourceContainer,
    private val mediaDataSourceContainer: MediaDataSourceContainer,
) : BaseRepository(), SeriesRepository {

    override suspend fun getTVShowsGenreList(): List<GenreDto>? {
        return service.getGenreTvShowList().body()?.genres
    }

    override suspend fun getOnTheAir(page: Int): List<TVShowsDTO> {
        return service.getOnTheAir(page).body()?.items ?: emptyList()
    }

    override suspend fun getAiringToday(page: Int): List<TVShowsDTO> {
        return service.getAiringToday(page).body()?.items ?: emptyList()
    }


    override suspend fun getTopRatedTvShow(page: Int): List<TVShowsDTO> {
        return service.getTopRatedTvShow(page).body()?.items ?: emptyList()
    }

    override suspend fun getPopularTvShow(page: Int): List<TVShowsDTO> {
        return service.getPopularTvShow(page).body()?.items ?: emptyList()
    }

    override suspend fun getTvShowDetails(tvShowId: Int): TvShowDetailsDto? {
        return service.getTvShowDetails(tvShowId).body()
    }

    override suspend fun getTvShowCast(tvShowId: Int): List<ActorDto>? {
        return service.getTvShowCast(tvShowId).body()?.cast
    }

    override suspend fun getTvShowReviews(tvShowId: Int): List<ReviewsDto> {
        return service.getTvShowReviews(tvShowId).body()?.items ?: emptyList()
    }

    override suspend fun setRating(tvShowId: Int, value: Float, sessionId: String): RatingDto? {
        return service.postTvShowRating(tvShowId, value, sessionId).body()
    }

    override suspend fun getRatedTvShow(accountId: Int, sessionId: String)
            : List<RatedTvShowDto>? {
        return service.getRatedTvShow(accountId, sessionId).body()?.items
    }

    override suspend fun getSeasonDetails(tvShowId: Int, seasonId: Int): SeasonDto? {
        return service.getSeasonDetails(tvShowId, seasonId).body()
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

    override suspend fun insertAiringToday(items: List<AiringTodaySeriesEntity>) {
        seriesDao.insertAiringTodaySeries(items)
    }

    override suspend fun deleteAiringToday() {
        seriesDao.deleteAllAiringTodaySeries()
    }

    override suspend fun insertOnTheAir(items: List<OnTheAirSeriesEntity>) {
        seriesDao.insertOnTheAirSeries(items)
    }

    override suspend fun deleteOnTheAir() {
        seriesDao.deleteAllOnTheAirSeries()
    }

    override suspend fun insertTopRatedTvShow(items: List<TopRatedSeriesEntity>) {
        seriesDao.insertTopRatedSeries(items)
    }

    override suspend fun deleteTopRatedTvShow() {
        seriesDao.deleteAllTopRatedSeries()
    }

    override fun getAiringToday(): Flow<List<AiringTodaySeriesEntity>> {
        return seriesDao.getAiringTodaySeries()
    }

    override fun getOnTheAir(): Flow<List<OnTheAirSeriesEntity>> {
        return seriesDao.getOnTheAirSeries()
    }

    override fun getTopRatedTvShow(): Flow<List<TopRatedSeriesEntity>> {
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
}