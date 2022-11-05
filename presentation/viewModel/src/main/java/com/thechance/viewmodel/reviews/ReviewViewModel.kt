package com.thechance.viewmodel.reviews

import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetReviewsUseCase
import com.thechance.ui.utilities.Constants
import com.thechance.viewmodel.BaseViewModel
import com.thechance.viewmodel.base.BaseInteractionListener
import com.thechance.viewmodel.movieDetails.mapper.ReviewUIStateMapper
import com.thechance.viewmodel.movieDetails.movieDetailsUIState.ErrorUIState
import com.thechance.viewmodel.movieDetails.movieDetailsUIState.MovieUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val getReviews: GetReviewsUseCase,
    private val reviewUIStateMapper: ReviewUIStateMapper,
) : BaseViewModel(), BaseInteractionListener {

    private val _uiState = MutableStateFlow(MovieUIState())
    val uiState: StateFlow<MovieUIState> = _uiState.asStateFlow()

    private lateinit var type: com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.MediaType
    private var mediaId = -1

    fun setData(
        type: com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.MediaType,
        mediaId: Int
    ) {
        this.type = type
        this.mediaId = mediaId
        getData()
    }


    override fun getData() {
        _uiState.update { it.copy(isLoading = true, errorUIStates = emptyList()) }
        viewModelScope.launch {
            try {
                val result = getReviews(type.name, mediaId)
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
