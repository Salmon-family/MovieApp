package com.thechance.movie.ui.reviews

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thechance.movie.ui.base.BaseInteractionListener
import com.thechance.movie.ui.base.BaseViewModel
import com.thechance.movie.ui.movieDetails.mapper.ReviewUIStateMapper
import com.thechance.movie.ui.movieDetails.movieDetailsUIState.ErrorUIState
import com.thechance.movie.ui.movieDetails.movieDetailsUIState.MovieUIState
import com.thechance.movie.utilities.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val getReviews: com.devfalah.usecases.GetReviewsUseCase,
    private val reviewUIStateMapper: ReviewUIStateMapper,
    state: SavedStateHandle
) : BaseViewModel(), BaseInteractionListener {

    private val args = ReviewFragmentArgs.fromSavedStateHandle(state)

    private val _uiState = MutableStateFlow(MovieUIState())
    val uiState: StateFlow<MovieUIState> = _uiState.asStateFlow()

    init {
        getData()
    }

    override fun getData() {
        _uiState.update { it.copy(isLoading = true, errorUIStates = emptyList()) }
        viewModelScope.launch {
            try {
                val result = getReviews(args.type, args.mediaId)
                _uiState.update {
                    it.copy(
                        movieReview = result.map { review -> reviewUIStateMapper.map(review) },
                        isLoading = false,
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorUIStates = onAddMessageToListError(e), isLoading = false)
                }
            }
        }
    }

    private fun onAddMessageToListError(e: Exception): List<ErrorUIState> {
        return listOf(
            ErrorUIState(
                code = Constants.INTERNET_STATUS,
                message = e.message.toString()
            )
        )
    }
}
