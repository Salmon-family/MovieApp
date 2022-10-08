package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.AddMovieDto
import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.CreatedListDto
import com.karrar.movieapp.data.remote.response.ListDetailsDto
import com.karrar.movieapp.data.remote.response.movie.RatedMovie
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.*
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.domain.mappers.ActorDetailsMapper
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.mappers.ActorMapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.MovieMapper
import com.karrar.movieapp.domain.mappers.PopularMovieMapper
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.PopularMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val actorDetailsMapper: ActorDetailsMapper,
    private val actorMapper: ActorMapper,
    private val genreMapper: GenreMapper,
    private val movieMapper: MovieMapper,
    private val tvShowsMapper: TVShowMapper,
    private val personMapper: PersonMapper,
    private val seriesMapper: SearchSeriesMapper,
    private val movieDao: MovieDao,
    private val searchHistoryMapper: SearchHistoryMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val reviewMapper: ReviewMapper,
    private val trailerMapper: TrailerMapper,
    private val popularMovieMapper: PopularMovieMapper,
    private val accountMapper: AccountMapper,
    private val ratedMoviesMapper: RatedMoviesMapper,
) : BaseRepository(), MovieRepository {
    override fun getPopularMovies(): Flow<State<List<PopularMovie>>> {
        return flow {
            emit(State.Loading)
            try {
                val responseGenre = movieService.getGenreList().body()?.genres
                val responseMovie = movieService.getPopularMovies().body()?.items

                if (responseMovie != null && responseGenre != null) {
                    emit(
                        State.Success(
                            popularMovieMapper.mapGenreMovie(responseMovie, responseGenre)
                        )
                    )
                } else
                    emit(State.Error("Mapping error"))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }

    override fun getTrendingActors(): Flow<State<List<Actor>>> {
        return wrap({ movieService.getTrendingActors() }) { response ->
            response.items?.map { actorMapper.map(it) } ?: emptyList()
        }
    }

    override fun getActorDetails(actorId: Int): Flow<State<ActorDetails>> {
        return wrap({ movieService.getActorDetails(actorId) }, { actorDetailsMapper.map(it) })
    }

    override fun getActorMovies(actorId: Int): Flow<State<List<Media>>> {
        return wrap({ movieService.getActorMovies(actorId) }, { actorMoviesDto ->
            actorMoviesDto.cast?.mapNotNull { cast ->
                cast?.let { movieMapper.map(it) }
            } ?: emptyList()
        })
    }

    override fun getUpcomingMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getUpcomingMovies() }, { baseResponse ->
            baseResponse.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getTopRatedMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getTopRatedMovies() }, { baseResponse ->
            baseResponse.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun searchForPerson(query: String): Flow<State<List<MediaInfo>>> {
        return wrap({ movieService.searchForPerson(query) }, { response ->
            response.items?.map {
                it.let { personMapper.map(it) }
            } ?: emptyList()
        })
    }

    override fun getNowPlayingMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getNowPlayingMovies() }, { response ->
            response.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun searchForMovie(query: String): Flow<State<List<MediaInfo>>> {
        return wrap({ movieService.searchForMovie(query) }, { response ->
            response.items?.map { seriesMapper.map(it) } ?: emptyList()
        })
    }

    override fun searchForSeries(query: String): Flow<State<List<MediaInfo>>> {
        return wrap({ movieService.searchForSeries(query) }, { response ->
            response.items?.map { seriesMapper.map(it) } ?: emptyList()
        })
    }

    override fun getAllSearchHistory(): Flow<List<SearchHistory>> {
        return movieDao.getAllSearchHistory().map { response ->
            response.map { searchHistoryMapper.map(it) }
        }
    }

    override fun getTrendingMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getTrendingMovies() }, { response ->
            response.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getMovieGenreList(): Flow<State<List<Genre>>> {
        return wrap({ movieService.getGenreList() }, { response ->
            response.genres?.map { genreMapper.map(it) } ?: emptyList()
        })
    }

    override fun getMovieListByGenreID(genreID: Int): Flow<State<List<Media>>> {
        return wrap({ movieService.getMovieListByGenre(genreID) }, { response ->
            response.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getDailyTrending(): Flow<State<List<Media>>> {
        return wrap({ movieService.getDailyTrending() }, { response ->
            response.items?.map { tvShowsMapper.map(it) } ?: emptyList()
        })
    }

    override fun getAllMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getAllMovies() }, { response ->
            response.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }


    override fun getMovieDetails(movieId: Int): Flow<State<MovieDetails>> {
        return wrap({ movieService.getMovieDetails(movieId) }, { response ->
            movieDetailsMapper.map(response)
        })
    }

    override fun getMovieCast(movieId: Int): Flow<State<List<Actor>>> {
        return wrap({ movieService.getMovieCast(movieId) }, { response ->
            response.cast?.map { actorMapper.map(it) } ?: emptyList()
        })
    }

    override fun getSimilarMovie(movieId: Int): Flow<State<List<Media>>> {
        return wrap({ movieService.getSimilarMovie(movieId) }, { response ->
            response.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getMovieReviews(movieId: Int): Flow<State<List<Review>>> {
        return wrap({ movieService.getMovieReviews(movieId) }, { response ->
            response.items?.map { reviewMapper.map(it) } ?: emptyList()
        })
    }

    override fun setRating(movieId: Int, value: Float, session_id: String): Flow<State<RatingDto>> {
        return wrapWithFlow { movieService.postRating(movieId, value, session_id) }
    }

    override fun getMovieTrailer(movieId: Int): Flow<State<Trailer>> {
        return wrap({ movieService.getMovieTrailer(movieId) }, {
            trailerMapper.map(it)
        })
    }

    override fun getAllLists(
        accountId: Int,
        sessionId: String,
    ): Flow<State<BaseResponse<CreatedListDto>>> {
        return wrapWithFlow { movieService.getCreatedLists(accountId, sessionId) }
    }

    override fun addMovieToList(
        sessionId: String,
        listId: Int,
        movieId: Int,
    ): Flow<State<AddMovieDto>> {
        return wrapWithFlow { movieService.addMovieToList(listId, sessionId, movieId) }
    }

    override fun getListDetails(listId: Int): Flow<State<ListDetailsDto>> {
        return wrapWithFlow { movieService.getList(listId) }
    }

    override fun getRatedMovie(
        accountId: Int,
        sessionId: String
    ): Flow<State<BaseResponse<RatedMovie>>> {
        return wrapWithFlow { movieService.getRatedMovie(accountId, sessionId) }
    }

    override suspend fun insertSearchItem(item: SearchHistoryEntity) {
        return movieDao.insert(item)
    }

    override suspend fun deleteSearchItem(item: SearchHistoryEntity) {
        return movieDao.delete(item)
    }

    override fun getAccountDetails(sessionId: String): Flow<State<Account>> {
        return wrap({movieService.getAccountDetails(sessionId)}, accountMapper::map)
    }

    override fun getRatedMovies(sessionId: String?): Flow<State<List<RatedMovies>>> {
        return wrap({movieService.getRatedMovies(sessionId)}){ response ->
            response.items?.map {
                ratedMoviesMapper.map(it)
            } ?: emptyList()
        }
    }

    override suspend fun insertMovie(movie: WatchHistoryEntity) = movieDao.insert(movie)

    override fun getAllWatchedMovies(): Flow<List<WatchHistoryEntity>> {
        return movieDao.getAllWatchedMovies()
    }
}