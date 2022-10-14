package com.karrar.movieapp.ui.movieDetails.movieReviews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Review
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    state: SavedStateHandle
) : BaseViewModel(), BaseInteractionListener {

    private val args = ReviewFragmentArgs.fromSavedStateHandle(state)

    private var _movieReviews = MutableLiveData<UIState<List<Review>>>()
    val movieReviews = _movieReviews.toLiveData()

    init {
        getData()
    }

    override fun getData() {
        _movieReviews.postValue(UIState.Loading)
        wrapWithState(
            {
                val response = movieRepository.getMovieReviews(args.mediaId)
                if (response.isNotEmpty()) {
                    _movieReviews.postValue(UIState.Success(response))
                }
            }, { _movieReviews.postValue(UIState.Error("")) })
    }

}
