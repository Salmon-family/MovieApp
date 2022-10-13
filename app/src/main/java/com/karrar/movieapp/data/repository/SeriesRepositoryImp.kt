package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.*
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val service: MovieService,
    private val mediaMapper: TVShowMapper,
    private val genreMapper: GenreMapper,
    private val tvShowDetailsMapper: TvShowDetailsMapper,
    private val actorMapper: ActorMapper,
    private val reviewMapper: ReviewMapper,
    private val seasonMapper: SeasonMapper,
    private val trailerMapper: TrailerMapper,
    private val ratedMoviesMapper: RatedMoviesMapper,
    private val movieDao: MovieDao
) : BaseRepository(), SeriesRepository {

    override fun getOnTheAir(): Flow<State<List<Media>>> {
        return wrap({ service.getOnTheAir() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getOnTheAir2(): List<Media> {
        return wrap2({ service.getOnTheAir() },
            { ListMapper(mediaMapper).mapList(it.items) }) ?: emptyList()

    }

    override fun getAiringToday(): Flow<State<List<Media>>> {
        return wrap({ service.getAiringToday() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getAiringToday2(): List<Media> {
        return wrap2({ service.getAiringToday() },
            { ListMapper(mediaMapper).mapList(it.items) }) ?: emptyList()
    }

    override fun getTopRatedTvShow(): Flow<State<List<Media>>> {
        return wrap({ service.getTopRatedTvShow() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getTopRatedTvShow2(): List<Media> {
        return wrap2({ service.getTopRatedTvShow() },
            { ListMapper(mediaMapper).mapList(it.items) }) ?: emptyList()
    }

    override fun getPopularTvShow(): Flow<State<List<Media>>> {
        return wrap({ service.getPopularTvShow() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getLatestTvShows(): Flow<State<List<Media>>> {
        return wrap({ service.getLatestTvShow() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getTVShowsGenreList(): List<Genre> {
        return wrap2({ service.getGenreTvShowList() },
            { ListMapper(genreMapper).mapList(it.genres) })
    }

    override suspend fun getTvShowsByGenreID(genreId: Int): List<Media> {
        return wrap2({ service.getTvListByGenre(genreId) }, {
            ListMapper(mediaMapper).mapList(it.items)
        })
    }

    override suspend fun getAllTvShows(): List<Media> {
        return wrap2({ service.getAllTvShows() }, {
            ListMapper(mediaMapper).mapList(it.items)
        })
    }

    override suspend fun getTvShowDetails(tvShowId: Int): TvShowDetails {
        return wrap2({ service.getTvShowDetails(tvShowId) }, { response ->
            tvShowDetailsMapper.map(response)
        })
    }

    override suspend fun getTvShowCast(tvShowId: Int): List<Actor> {
        return wrap2({ service.getTvShowCast(tvShowId) }, { response ->
            response.cast?.map { actorMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getTvShowReviews(tvShowId: Int): List<Review> {
        return wrap2({ service.getTvShowReviews(tvShowId) }, { response ->
            response.items?.map { reviewMapper.map(it) } ?: emptyList()
        })
    }

    override fun setRating(tvShowId: Int, value: Float, sessionId: String): Flow<State<RatingDto>> {
        return wrapWithFlow { service.postRating(tvShowId, value, sessionId) }
    }

    override suspend fun getRatedTvShow(accountId: Int, sessionId: String): List<RatedMovies> {
        return wrap2({ service.getRatedTvShow(accountId, sessionId) }, { baseResponse ->
            baseResponse.items?.map { ratedMoviesMapper.map(it) } ?: emptyList()
        })
    }

    override suspend fun getSeasonDetails(tvShowId: Int, seasonId: Int): Season {
        return wrap2({ service.getSeasonDetails(tvShowId, seasonId) }, { response ->
            seasonMapper.map(response)
        })
    }

    override suspend fun getTvShowTrailer(tvShowId: Int): Trailer {
        return wrap2({ service.getTvShowTrailer(tvShowId) }, { response ->
            trailerMapper.map(response)
        })
    }

    override suspend fun insertTvShow(tvShow: WatchHistoryEntity) {
            return movieDao.insert(tvShow)
    }

}