package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.utilities.Event
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

     private var _movieReviews = MutableLiveData<State<List<Review>>>()
     val movieReviews :LiveData<State<List<Review>>> = _movieReviews

     private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
     var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

     private val _clickMovieEvent = MutableLiveData<Event<Int>>()
     var clickMovieEvent: LiveData<Event<Int>> = _clickMovieEvent

     private val _clickCastEvent = MutableLiveData<Event<Int>>()
     var clickCastEvent: LiveData<Event<Int>> = _clickCastEvent

     var ratingValue = MutableLiveData<Float>()

     var rating = movieRepository.setRating(760161, 8.0).asLiveData()

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

     fun onclickBack() {
          _clickBackEvent.postValue(Event(true))
     }

     override fun onClickCast(cast_id: Int) {
          _clickCastEvent.postValue(Event(cast_id))

     }

     override fun onClickMovie(movie_id: Int) {
          _clickMovieEvent.postValue(Event(movie_id))
     }

}