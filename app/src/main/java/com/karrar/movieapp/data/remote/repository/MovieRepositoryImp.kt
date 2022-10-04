package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.MovieApplication
import com.karrar.movieapp.data.local.database.MovieDataBase
import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.response.PersonDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.*
import com.karrar.movieapp.domain.models.*
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val personMapper: PersonMapper,
    private val actorDetailsMapper: ActorDetailsMapper,
    private val actorMoviesMapper: ActorMoviesMapper,
    private val movieMapper: MovieMapper,
    private val seriesMapper: SeriesMapper,
    private val movieDao: MovieDao,
    private val searchHistoryMapper: SearchHistoryMapper
) : BaseRepository(),MovieRepository {
    override fun getPopularMovies(): Flow<State<BaseResponse<MovieDto>>> {
        return wrapWithFlow { movieService.getPopularMovies() }
    }

    override fun getUpcomingMovies(): Flow<State<BaseResponse<MovieDto>>> {
        return wrapWithFlow { movieService.getUpcomingMovies() }
    }

    override fun getTopRatedMovies(): Flow<State<BaseResponse<MovieDto>>> {
        return wrapWithFlow { movieService.getTopRatedMovies() }
    }

    override fun getNowPlayingMovies(): Flow<State<BaseResponse<MovieDto>>> {
        return wrapWithFlow { movieService.getNowPlayingMovies() }
    }

    override fun getTrendingMovies(): Flow<State<BaseResponse<MovieDto>>> {
        return wrapWithFlow { movieService.getTrendingMovies() }
    }

    override fun getTrendingPersons(): Flow<State<BaseResponse<PersonDto>>> {
        return wrapWithFlow { movieService.getTrendingPersons() }
    }

    override fun getActorDetails(actorId: Int): Flow<State<ActorDetails>> {
        return wrap({ movieService.getActorDetails(actorId) }, { actorDetailsMapper.map(it) })
    }

    override fun getMovieDetails(actorId: Int): Flow<State<List<Movie>>> {
        return wrap({ movieService.getActorMovies(actorId) }, {
            it.cast?.map {
                actorMoviesMapper.map(it!!)
            } ?: emptyList()
        })
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

    override fun searchForPerson(query: String): Flow<State<List<Media>>> {
        return wrap({ movieService.searchForPerson(query) }, {
            it.items?.map { personMapper.map(it!!) } ?: emptyList()
        })
    }

    override fun searchForMovie(query: String): Flow<State<List<Media>>> {
        return wrap({ movieService.searchForMovie(query) }, {
            it.items?.map { movieMapper.map(it!!) } ?: emptyList()
        })
    }

    override fun searchForSeries(query: String): Flow<State<List<Media>>> {
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
}