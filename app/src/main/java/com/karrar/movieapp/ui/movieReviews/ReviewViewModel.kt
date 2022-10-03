package com.karrar.movieapp.ui.movieReviews

import androidx.lifecycle.*
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.Review
import com.karrar.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ReviewViewModel @Inject constructor(private val movieRepository: MovieRepository
): BaseViewModel(), BaseInteractionListener{

    private var _movieReviews = MutableLiveData<State<List<Review>>>()
    val movieReviews : LiveData<State<List<Review>>> = _movieReviews

    fun getAllReviews(movie_id:Int){
        collectResponse(movieRepository.getMovieReviews(movie_id)){
            _movieReviews.postValue(it)
        }
    }

}