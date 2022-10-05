package com.karrar.movieapp.ui.movieDetails

import android.util.Log
import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.response.RatedMovie
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
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

     private val _check = MutableLiveData(false)
     var check: LiveData<Boolean> = _check

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
          collectResponse(movieRepository.getRatedMovie(14012083, "1d92e6a329c67e2e5e0486a0a93d5980711535b1")){
               checkIfMovieRated(it.toData()?.items, movie_id)
          }

     }

     private fun checkIfMovieRated(items: List<RatedMovie>?, movie_id: Int){
          items?.map {
               if(it.id == movie_id){
                    ratingValue.postValue(it.rating?.toFloat())
               }
          }
     }

     fun onAddRating(movie_id: Int, value: Float) {
          if (_check.value == true){
               collectResponse(movieRepository.setRating(movie_id, value, "1d92e6a329c67e2e5e0486a0a93d5980711535b1")){ }
          } else{ _check.postValue(true) }
     }


     fun onClickSave(){
          _clickSaveEvent.postValue(Event(true))
     }

     fun onClickPlayTrailer(){
          _clickPlayTrailerEvent.postValue(Event(true))
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