package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.ActorMapper
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.MovieMapper
import com.karrar.movieapp.domain.mappers.PopularMovieMapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.domain.models.PopularMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

    override fun getPopularMovies(): Flow<State<List<PopularMovie>>> {
        val mapper = PopularMovieMapper()
        val mapperGenre = GenreMapper()

        return flow {
            emit(State.Loading)
            try {
                val responseGenre = movieService.getGenreList()
                val genreList = responseGenre.body()?.genres?.map { mapperGenre.map(it) }
                val responseMovie = movieService.getPopularMovies()
                val items = responseMovie.body()?.items?.map { mapper.map(it) }
                val movieWithGenre = mapper.mapGenreMovie(items!!, genreList!!)
                emit(State.Success(movieWithGenre))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }

    override fun getUpcomingMovies(): Flow<State<List<Movie>>> {
        val mapper = MovieMapper()
        return flow {
            emit(State.Loading)
            try {
                val response = movieService.getUpcomingMovies()
                val items = response.body()?.items?.map { mapper.map(it) }
                emit(State.Success(items))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }

    override fun getTopRatedMovies(): Flow<State<BaseResponse<MovieDto>>> {
        return wrapWithFlow { movieService.getTopRatedMovies() }
    }

    override fun getNowPlayingMovies(): Flow<State<List<Movie>>> {
        val mapper = MovieMapper()
        return flow {
            emit(State.Loading)
            try {
                val response = movieService.getNowPlayingMovies()
                val items = response.body()?.items?.map { mapper.map(it) }
                emit(State.Success(items))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }

    override fun getTrendingMovies(): Flow<State<List<Movie>>> {
        val mapper = MovieMapper()
        return flow {
            emit(State.Loading)
            try {
                val response = movieService.getTrendingMovies()
                val items = response.body()?.items?.map { mapper.map(it) }
                emit(State.Success(items))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }

    override fun getTrendingPersons(): Flow<State<List<Actor>>> {
        val mapper = ActorMapper()
        return flow {
            emit(State.Loading)
            try {
                val response = movieService.getTrendingPersons()
                val items = response.body()?.items?.map { mapper.map(it) }
                emit(State.Success(items))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
    }

    override fun getGenreList(): Flow<State<List<Genre>>> {
        val mapper = GenreMapper()
        return flow {
            emit(State.Loading)
            try {
                val genreList = movieService.getGenreList()
                val items = genreList.body()?.genres?.map { mapper.map(it) }
                emit(State.Success(items))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }
        }
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