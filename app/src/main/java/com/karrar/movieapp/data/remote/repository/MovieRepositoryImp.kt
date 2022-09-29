package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.data.remote.response.movieDetailsDto.MovieDetailsDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.CastMapper
import com.karrar.movieapp.domain.models.Cast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import kotlin.reflect.KFunction2

class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val castMapper: CastMapper
) : MovieRepository {
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

    override fun getMovieDetails(movie_id: Int): Flow<State<MovieDetailsDto>> {
        return wrapWithFlow { movieService.getMovieDetails(movie_id) }
    }


    override fun getMovieCast(movie_id: Int): Flow<State<List<Cast>>> {
         return flow {
            emit(State.Loading)
            try {
                val response = movieService.getMovieCast(movie_id)
                val items = response.body()?.cast?.map { castMapper.map(it) }
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