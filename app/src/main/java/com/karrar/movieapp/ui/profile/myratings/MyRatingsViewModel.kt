package com.karrar.movieapp.ui.profile.myratings

import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.domain.models.Rated
import com.karrar.movieapp.domain.GetListOfRatedUseCase
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyRatingsViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val getRatedUseCase: GetListOfRatedUseCase
) : BaseViewModel(), RatedMoviesInteractionListener {

    private val _ratedUiState = MutableStateFlow(MyRateUIState())
    val ratedUiState: StateFlow<MyRateUIState> = _ratedUiState

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    private val _clickTVShowEvent = MutableLiveData<Event<Int>>()
    val clickTVShowEvent = _clickTVShowEvent.toLiveData()

    init {
        getData()
    }

    override fun getData() {
        _ratedUiState.update { it.copy(isLoading = true) }
        wrapWithState({
            val sessionId = accountRepository.getSessionId()
            sessionId?.let { session ->
                val listOfRated = getRatedUseCase(0, session).map { rate -> rate.ratedToUiSate() }
                _ratedUiState.update { it.copy(ratedList = listOfRated, isLoading = false) }
            }
        }, { _ratedUiState.update { it.copy(error = listOf(Error(""))) } })
    }

    override fun onClickMovie(mediaID: Int) {
        ratedUiState.value.ratedList.let { it ->
            val item = it.find { it.id == mediaID }
            item?.let {
                if (it.mediaType == Constants.MOVIE) _clickMovieEvent.postEvent(mediaID)
                else _clickTVShowEvent.postEvent(mediaID)
            }
        }
    }
}