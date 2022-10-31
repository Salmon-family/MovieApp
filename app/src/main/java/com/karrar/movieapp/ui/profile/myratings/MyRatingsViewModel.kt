package com.karrar.movieapp.ui.profile.myratings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.thechance.repository.AccountRepository
import com.karrar.movieapp.domain.GetListOfRatedUseCase
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRatingsViewModel @Inject constructor(
    private val accountRepository: com.thechance.repository.AccountRepository,
    private val getRatedUseCase: GetListOfRatedUseCase,
    private val ratedUIStateMapper: RatedUIStateMapper
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
        viewModelScope.launch {
            _ratedUiState.update { it.copy(isLoading = true) }
            try {
                val sessionId = accountRepository.getSessionId()
                sessionId?.let { session ->
                    val listOfRated =
                        getRatedUseCase(0, session).map { rate -> ratedUIStateMapper.map(rate) }
                    _ratedUiState.update { it.copy(ratedList = listOfRated, isLoading = false) }
                }
            } catch (e: Throwable) {
                _ratedUiState.update { it.copy(error = listOf(Error("")), isLoading = false) }
            }
        }
    }


    override fun onClickMovie(movieId: Int) {
        ratedUiState.value.ratedList.let { it ->
            val item = it.find { it.id == movieId }
            item?.let {
                if (it.mediaType == Constants.MOVIE) _clickMovieEvent.postEvent(movieId)
                else _clickTVShowEvent.postEvent(movieId)
            }
        }
    }

    fun retryConnect() {
        _ratedUiState.update { it.copy(error = emptyList()) }
        getData()
    }
}