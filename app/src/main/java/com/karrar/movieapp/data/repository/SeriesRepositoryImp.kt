package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.movie.RatedMovie
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
    private val trailerMapper: TrailerMapper
) : BaseRepository(), SeriesRepository {

    override fun getOnTheAir(): Flow<State<List<Media>>> {
        return wrap({ service.getOnTheAir() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getAiringToday(): Flow<State<List<Media>>> {
        return wrap({ service.getAiringToday() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTopRatedTvShow(): Flow<State<List<Media>>> {
        return wrap({ service.getTopRatedTvShow() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
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

    override fun getTVShowsGenreList(): Flow<State<List<Genre>>> {
        return wrap({ service.getGenreTvShowList() }, { response ->
            response.genres?.map { genreMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTvShowsByGenreID(genreId: Int): Flow<State<List<Media>>> {
        return wrap({ service.getTvListByGenre(genreId) }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getAllTvShows(): Flow<State<List<Media>>> {
        return wrap({ service.getAllTvShows() }, { response ->
            response.items?.map { mediaMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTvShowDetails(tvShowId: Int): Flow<State<TvShowDetails>> {
        return wrap({ service.getTvShowDetails(tvShowId) }, { response ->
            tvShowDetailsMapper.map(response)
        })
    }

    override fun getTvShowCast(tvShowId: Int): Flow<State<List<Actor>>> {
        return wrap({ service.getTvShowCast(tvShowId) }, { response ->
            response.cast?.map { actorMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTvShowReviews(tvShowId: Int): Flow<State<List<Review>>> {
        return wrap({ service.getTvShowReviews(tvShowId) }, { response ->
            response.items?.map { reviewMapper.map(it) } ?: emptyList()
        })
    }

    override fun setRating(tvShowId: Int, value: Float, sessionId: String): Flow<State<RatingDto>> {
        return wrapWithFlow { service.postRating(tvShowId, value, sessionId) }
    }

    override fun getRatedTvShow(
        accountId: Int,
        sessionId: String
    ): Flow<State<BaseResponse<RatedMovie>>> {
        return wrapWithFlow { service.getRatedTvShow(accountId, sessionId) }
    }

    override fun getSeasonDetails(tvShowId: Int, seasonId: Int): Flow<State<Season>> {
        return wrap({ service.getSeasonDetails(tvShowId, seasonId) }, { response ->
            seasonMapper.map(response)
        })
    }

    override fun getTvShowTrailer(tvShowId: Int): Flow<State<Trailer>> {
        return wrap({ service.getTvShowTrailer(tvShowId) }, { response ->
            trailerMapper.map(response)
        })
    }

}