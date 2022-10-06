package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.MovieApplication
import com.karrar.movieapp.data.local.database.MovieDataBase
import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.AddMovieDto
import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.CreatedListDto
import com.karrar.movieapp.data.remote.response.ListDetailsDto
import com.karrar.movieapp.data.remote.response.RatedMovie
import com.karrar.movieapp.data.remote.response.movieDetailsDto.RatingDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.*
import com.karrar.movieapp.domain.models.*
import dagger.Provides
import com.karrar.movieapp.domain.mappers.ActorDetailsMapper
import com.karrar.movieapp.domain.mappers.ActorMoviesMapper
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.domain.mappers.ActorMapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.MediaMapper
import com.karrar.movieapp.domain.mappers.PopularMovieMapper
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.PopularMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val actorDetailsMapper: ActorDetailsMapper,
    private val actorMoviesMapper: ActorMoviesMapper,
    private val actorMapper: ActorMapper,
    private val castMapper: CastMapper,
    private val genreMapper: GenreMapper,
    private val movieMapper: MediaMapper,
    private val personMapper: PersonMapper,
    private val moviesMapper: MovieMapper,
    private val seriesMapper: SeriesMapper,
    private val movieDao: MovieDao,
    private val searchHistoryMapper: SearchHistoryMapper,
    private val trendMapper: TrendMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val reviewMapper: ReviewMapper,
    private val trailerMapper: TrailerMapper
) : BaseRepository(),MovieRepository {
    override fun getPopularMovies(): Flow<State<List<PopularMovie>>> {
        val mapper = PopularMovieMapper()
        return flow {
            emit(State.Loading)
            try {
                val responseGenre = movieService.getGenreList().body()?.genres
                val responseMovie = movieService.getPopularMovies().body()?.items

                if (responseMovie != null && responseGenre != null) {
                    emit(State.Success(mapper.mapGenreMovie(responseMovie, responseGenre)))
                } else
                    emit(State.Error("Mapping error"))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }

    override fun getTrendingActors(): Flow<State<List<Actor>>> {
        return wrap({ movieService.getTrendingActors() }) { it ->
            it.items?.map { actorMapper.map(it) } ?: emptyList()
        }
    }

    override fun getActorDetails(actorId: Int): Flow<State<ActorDetails>> {
        return wrap({ movieService.getActorDetails(actorId) }, { actorDetailsMapper.map(it) })
    }

    override fun getActorMovies(actorId: Int): Flow<State<List<Media?>>> {
        return wrap({ movieService.getActorMovies(actorId) }, { actorMoviesDto ->
            actorMoviesDto.cast?.map { cast ->
                cast?.let {
                    actorMoviesMapper.map(it)
                }
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
        return wrap({ movieService.searchForPerson(query) }, {
            it.items?.map { personMapper.map(it!!) } ?: emptyList()
        })
    }

    override fun getNowPlayingMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getNowPlayingMovies() }, { baseResponse ->
            baseResponse.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun searchForMovie(query: String): Flow<State<List<MediaInfo>>> {
        return wrap({ movieService.searchForMovie(query) }, {
            it.items?.map { moviesMapper.map(it!!) } ?: emptyList()
        })
    }

    override fun searchForSeries(query: String): Flow<State<List<MediaInfo>>> {
        return wrap({ movieService.searchForSeries(query) }, {
            it.items?.map { seriesMapper.map(it!!) } ?: emptyList()
        })
    }

    override suspend fun insertSearchItem(item: SearchHistoryEntity) {
        return movieDao.insert(item)
    }

    override suspend fun deleteSearchItem(item: SearchHistoryEntity) {
        return movieDao.delete(item)
    }

    override fun getAllSearchHistory(): Flow<List<SearchHistory>> {
        return movieDao.getAllSearchHistory().map {
            it.map { searchHistoryMapper.map(it)}
        }
    }

    override fun getTrendingMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getTrendingMovies() }, { baseResponse ->
            baseResponse.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getGenreList(): Flow<State<List<Genre>>> {
        return wrap({ movieService.getGenreList() }, { genreResponse ->
            genreResponse.genres?.map { genreMapper.map(it) } ?: emptyList()
        })
    }

    override fun getMovieListByGenre(genreID: Int): Flow<State<List<Media>>> {
        return wrap({ movieService.getMovieListByGenre(genreID) }, { baseResponse ->
            baseResponse.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getDailyTrending(): Flow<State<List<Trend>>> {
        return wrap({movieService.getDailyTrending()}, {
            it.items?.map { trendMapper.map(it) } ?: emptyList()
        })
    }

    override fun getAllMovies(): Flow<State<List<Media>>> {
        return wrap({ movieService.getAllMovies() }, {
            it.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }


    override fun getMovieDetails(movie_id: Int): Flow<State<MovieDetails>> {
        return wrap ({ movieService.getMovieDetails(movie_id) },{
            movieDetailsMapper.map(it)
        })
    }


    override fun getMovieCast(movie_id: Int): Flow<State<List<Cast>>> {
        return wrap ({ movieService.getMovieCast(movie_id) },{
            it.cast?.map { castMapper.map(it) } ?: emptyList()
        })
    }

    override fun getSimilarMovie(movie_id: Int): Flow<State<List<Media>>> {
        return wrap ({ movieService.getSimilarMovie(movie_id) },{
            it.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getMovieReviews(movie_id: Int): Flow<State<List<Review>>> {
        return wrap ({ movieService.getMovieReviews(movie_id) },{
            it.items?.map { reviewMapper.map(it) } ?: emptyList()
        })
    }

    override fun setRating(movie_id: Int, value: Float, session_id: String): Flow<State<RatingDto>> {
        return wrapWithFlow { movieService.postRating(movie_id, value, session_id) }
    }

    override fun getMovieTrailer(movie_id: Int): Flow<State<Trailer>> {
        return wrap({ movieService.getMovieTrailer(movie_id) },{
            trailerMapper.map(it)
        })
    }

    override fun getAllLists(accountId: Int, session_id: String, ): Flow<State<BaseResponse<CreatedListDto>>>{
        return wrapWithFlow { movieService.getCreatedLists(accountId, session_id) }
    }

    override fun addMovieToList(session_id: String, list_id: Int, movie_id: Int, ): Flow<State<AddMovieDto>> {
        return wrapWithFlow { movieService.addMovieToList(list_id, session_id, movie_id) }
    }

    override fun getListDetails(list_id: Int): Flow<State<ListDetailsDto>> {
        return wrapWithFlow { movieService.getList(list_id) }
    }

    override fun getRatedMovie(account_id: Int, session_id:String): Flow<State<BaseResponse<RatedMovie>>> {
        return wrapWithFlow { movieService.getRatedMovie(account_id, session_id) }
    }

    private fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<State<T>> {
        return flow {
            emit(State.Loading)
            try {
                val response = function()
                if (response.isSuccessful) {
                    emit(State.Success(response.body()))
                } else {
                    emit(State.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }
        }
    }

}