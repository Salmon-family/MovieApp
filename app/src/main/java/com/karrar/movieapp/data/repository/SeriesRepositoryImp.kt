package com.karrar.movieapp.data.repository

import androidx.paging.Pager
import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.daos.SeriesDao
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.series.AiringTodaySeriesEntity
import com.karrar.movieapp.data.local.database.entity.series.OnTheAirSeriesEntity
import com.karrar.movieapp.data.local.database.entity.series.TopRatedSeriesEntity
import com.karrar.movieapp.data.mediaDataSource.series.SeriesDataSourceContainer
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.MediaDataSourceContainer
import com.karrar.movieapp.domain.mappers.SeriesMapperContainer
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val seriesDao: SeriesDao,
    private val seriesMapperContainer: SeriesMapperContainer,
    private val seriesDataSourceContainer: SeriesDataSourceContainer,
    private val mediaDataSourceContainer: MediaDataSourceContainer,
) : BaseRepository(), SeriesRepository {

    override suspend fun getTVShowsGenreList(): List<Genre> {
        return wrap({ service.getGenreTvShowList() },
            { ListMapper(seriesMapperContainer.genreMapper).mapList(it.genres) })
    }

    override suspend fun getTVShowsGenreList2(): List<GenreDto>? {
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

    override suspend fun getRatedTvShow(accountId: Int, sessionId: String): List<Rated> {
        return wrap({ service.getRatedTvShow(accountId, sessionId) },
            { ListMapper(seriesMapperContainer.ratedTvShowMapper).mapList(it.items) })
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