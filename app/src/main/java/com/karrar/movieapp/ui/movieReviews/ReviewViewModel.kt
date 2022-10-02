package com.karrar.movieapp.ui.movieReviews

import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ReviewViewModel @Inject constructor(private val movieRepository: MovieRepository
): ViewModel(), BaseInteractionListener{


    private var _movieReviews = MutableLiveData<State<List<Review>>>()
    val movieReviews : LiveData<State<List<Review>>> = _movieReviews


    fun getAllReviews(movie_id:Int){
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

}