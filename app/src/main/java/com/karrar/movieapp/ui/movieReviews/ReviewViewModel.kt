package com.karrar.movieapp.ui.movieReviews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Review
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : BaseViewModel(), BaseInteractionListener {

    private var _movieReviews = MutableLiveData<UIState<List<Review>>>()
    val movieReviews = _movieReviews.toLiveData()

    fun getAllReviews(movie_id: Int) {
        _movieReviews.postValue(UIState.Loading)
        viewModelScope.launch {
            val response = movieRepository.getMovieReviews(movie_id)
            if (response.isNotEmpty()) {
                _movieReviews.postValue(UIState.Success(response))
            }

        }

    }
}

