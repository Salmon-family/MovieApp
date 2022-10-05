package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.data.remote.response.movieDetailsDto.RatingDto
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.*
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject



class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val castMapper: CastMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val movieMapper: MovieMapper,
    private val reviewMapper: ReviewMapper,
    private val trailerMapper: TrailerMapper
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

    override fun setRating(movie_id: Int, value: Float, guest_session_id: String): Flow<State<RatingDto>> {
        return wrapWithFlow { movieService.postRating(movie_id, value, guest_session_id) }
    }

    override fun getMovieTrailer(movie_id: Int): Flow<State<Trailer>> {
        return wrap({ movieService.getMovieTrailer(movie_id) },{
            trailerMapper.map(it)
        })
    }

    override fun getAllLists(accountId: Int, session_id: String, ): Flow<State<BaseResponse<CreatedListDto>>>{
        return wrapWithFlow { movieService.getCreatedLists(accountId, session_id) }
    }

    override fun createList(session_id: String, name: String, description: String): Flow<State<CreateListDto>> {
        return wrapWithFlow { movieService.createList(session_id, name, description) }
    }

    override fun addMovieToList(session_id: String, list_id: Int, movie_id: Int, ): Flow<State<AddMovieDto>> {
        return wrapWithFlow { movieService.addMovieToList(list_id, session_id, movie_id) }
    }

    override fun getListDetails(list_id: Int): Flow<State<ListDetailsDto>> {
        return wrapWithFlow { movieService.getList(list_id) }
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