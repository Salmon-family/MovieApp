package com.karrar.movieapp.ui.movieDetails

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieRepository: MovieRepository
): BaseViewModel(), CastInteractionListener, MovieInteractionListener{

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

     private val _clickPlayTrailerEvent = MutableLiveData<Event<Boolean>>()
     var clickPlayTrailerEvent: LiveData<Event<Boolean>> = _clickPlayTrailerEvent

     private val _clickReviewsEvent = MutableLiveData<Event<Boolean>>()
     var clickReviewsEvent: LiveData<Event<Boolean>> = _clickReviewsEvent

     private val _clickSaveEvent = MutableLiveData<Event<Boolean>>()
     var clickSaveEvent: LiveData<Event<Boolean>> = _clickSaveEvent


     var ratingValue = MutableLiveData<Float>()


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

     fun onClickSave(){
          _clickSaveEvent.postValue(Event(true))
     }

     fun onClickPlayTrailer(){
          _clickPlayTrailerEvent.postValue(Event(true))
     }

     fun onAddRating(movie_id: Int, value: Float) {
          movieRepository.setRating(movie_id, value, "e6e2f890a0ef87c0381061ccf8787494")
     }

     fun onclickBack() {
          _clickBackEvent.postValue(Event(true))
     }

     fun onclickViewReviews() {
          _clickReviewsEvent.postValue(Event(true))
     }

     override fun onClickCast(cast_id: Int) {
          _clickCastEvent.postValue(Event(cast_id))

     }

     override fun onClickMovie(movie_id: Int) {
          _clickMovieEvent.postValue(Event(movie_id))
     }

}