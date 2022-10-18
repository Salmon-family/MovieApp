package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.mappers.SeriesMapperContainer
import com.karrar.movieapp.domain.models.*
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val seriesMapperContainer: SeriesMapperContainer,
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

    override suspend fun getRatedTvShow(accountId: Int, sessionId: String): List<RatedMovies> {
        return wrap({ service.getRatedTvShow(accountId, sessionId) },
            { ListMapper(seriesMapperContainer.ratedMoviesMapper).mapList(it.items) })
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
}