package com.karrar.movieapp.data.remote.repository

import android.util.Log
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.CastMapper
import com.karrar.movieapp.domain.mappers.MovieDetailsMapper
import com.karrar.movieapp.domain.mappers.MovieMapper
import com.karrar.movieapp.domain.mappers.ReviewMapper
import com.karrar.movieapp.domain.models.Cast
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.domain.models.MovieDetails
import com.karrar.movieapp.domain.models.Review
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val castMapper: CastMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val movieMapper: MovieMapper,
    private val reviewMapper: ReviewMapper
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

    override fun getSimilarMovie(movie_id: Int): Flow<State<List<Movie>>> {
        return wrap ({ movieService.getSimilarMovie(movie_id) },{
            it.items?.map { movieMapper.map(it) } ?: emptyList()
        })
    }

    override fun getMovieReviews(movie_id: Int): Flow<State<List<Review>>> {
        return wrap ({ movieService.getMovieReviews(movie_id) },{
            it.items?.map { reviewMapper.map(it) } ?: emptyList()
        })
    }


    private fun <I, O> wrap(function: suspend () -> Response<I>,  mapper: (I) -> O,): Flow<State<O>> {
        return flow {
            emit(State.Loading)
            try {
                val response = function()
                if (response.isSuccessful) {
                    val items = response.body()?.let { mapper(it) }
                    emit(State.Success(items))
                } else {
                    emit(State.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
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