package com.thechance.viewmodel.profile.myratings

import androidx.lifecycle.viewModelScope
import com.thechance.viewmodel.BaseViewModel
import com.thechance.ui.utilities.Constants
import com.thechance.ui.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRatingsViewModel @Inject constructor(
    private val getRatedUseCase: com.devfalah.usecases.GetListOfRatedUseCase,
    private val ratedUIStateMapper: RatedUIStateMapper
) : BaseViewModel(), RatedMoviesInteractionListener {

    private val _ratedUiState = MutableStateFlow(MyRateUIState())
    val ratedUiState: StateFlow<MyRateUIState> = _ratedUiState

    private val _myRatingUIEvent: MutableStateFlow<Event<MyRatingUIEvent?>> =
        MutableStateFlow(Event(null))
    val myRatingUIEvent = _myRatingUIEvent.asStateFlow()

    init {
        getData()
    }

    override fun getData() {
        viewModelScope.launch {
            _ratedUiState.update { it.copy(isLoading = true) }
            try {
                val listOfRated =
                    getRatedUseCase().map { rate -> ratedUIStateMapper.map(rate) }
                _ratedUiState.update { it.copy(ratedList = listOfRated, isLoading = false) }
            } catch (e: Throwable) {
                _ratedUiState.update { it.copy(error = listOf(Error("")), isLoading = false) }
            }
        }
    }


    override fun onClickMovie(movieId: Int) {
        ratedUiState.value.ratedList.let { it ->
            val item = it.find { it.id == movieId }
            item?.let {
                if (it.mediaType == Constants.MOVIE) {
                    _myRatingUIEvent.update { Event(MyRatingUIEvent.MovieEvent(movieId)) }
                } else {
                    _myRatingUIEvent.update { Event(MyRatingUIEvent.TVShowEvent(movieId)) }
                }
            }
        }
    }

    fun retryConnect() {
        _ratedUiState.update { it.copy(error = emptyList()) }
        getData()
    }
}