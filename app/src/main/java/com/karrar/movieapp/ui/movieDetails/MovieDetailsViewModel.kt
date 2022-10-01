package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.movieDetailsDto.reviews.ReviewsDto
import com.karrar.movieapp.domain.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieRepository: MovieRepository
): ViewModel(), CastInteractionListener, MovieInteractionListener{

     private var _movieDetails = MutableLiveData<State<MovieDetails>>()
     val movieDetails :LiveData<State<MovieDetails>> = _movieDetails

     private var _movieCast = MutableLiveData<State<List<Cast>>>()
     val movieCast :LiveData<State<List<Cast>>> = _movieCast

     private var _similarMovie = MutableLiveData<State<List<Movie>>>()
     val similarMovie :LiveData<State<List<Movie>>> = _similarMovie

     private var _movieReviews = MutableLiveData<State<BaseResponse<ReviewsDto>>>()
     val movieReviews :LiveData<State<BaseResponse<ReviewsDto>>> = _movieReviews


     fun getAllDetails(movie_id:Int){
          collectResponse(movieRepository.getMovieDetails(movie_id)) {
               _movieDetails.postValue(it)
          }
          collectResponse(movieRepository.getMovieCast(movie_id)){
               _movieCast.postValue(it)
          }
          collectResponse(movieRepository.getSimilarMovie(movie_id)){
               _similarMovie.postValue(it)
          }
          collectResponse(movieRepository.getMovieReviews(movie_id)){
               _movieReviews.postValue(it)
          }
     }


     private fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
          viewModelScope.launch {
               flow.flowOn(Dispatchers.IO)
                    .collect { state ->
                         function(state)
                    }
          }
     }

     override fun onClickCast(cast_id: Int) { }
     override fun onClickMovie(cast_id: Int) { }

}