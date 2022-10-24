package com.karrar.movieapp.ui.movieDetails.movieReviews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.karrar.movieapp.domain.models.Review
import com.karrar.movieapp.domain.usecase.movieDetails.GetMovieReviewsUseCase
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.movieDetails.MovieDetailsUIState
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    state: SavedStateHandle
) : BaseViewModel(), BaseInteractionListener {

    private val args = ReviewFragmentArgs.fromSavedStateHandle(state)

    private val _uiState = MutableStateFlow(MovieDetailsUIState())
    val uiState: StateFlow<MovieDetailsUIState> = _uiState.asStateFlow()


    private var _movieReviews = MutableLiveData<UIState<List<Review>>>()
    val movieReviews = _movieReviews.toLiveData()

    init {
        getData()
    }

    override fun getData() {
        _movieReviews.postValue(UIState.Loading)
        wrapWithState(
            {
                _uiState.update {
                    it.copy(
                        movieReview = getMovieReviewsUseCase(args.mediaId),
                        isLoading = false
                    )
                }
                if (_uiState.value.movieReview.isNotEmpty()) {
                    _movieReviews.postValue(UIState.Success(_uiState.value.movieReview))
                }
            },
            { _movieReviews.postValue(UIState.Error(_uiState.value.errors.joinToString { it.message })) })
    }
}
