package com.karrar.movieapp.ui.movieReviews

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.models.Review
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    state: SavedStateHandle
) : BaseViewModel(), BaseInteractionListener {

    val args = ReviewFragmentArgs.fromSavedStateHandle(state)

    private var _movieReviews = MutableLiveData<State<List<Review>>>()
    val movieReviews : LiveData<State<List<Review>>> = _movieReviews


    init {
        getAllReviews(args.mediaId)
    }

    private fun getAllReviews(mediaId: Int) {
        when (args.type) {
            MediaType.MOVIE -> collectResponse(movieRepository.getMovieReviews(mediaId)) {
                _movieReviews.postValue(it)
            }
            MediaType.TV_SHOW -> collectResponse(seriesRepository.getTvShowReviews(mediaId)) {
                _movieReviews.postValue(it)
            }
        }
    }

}