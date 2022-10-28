package com.karrar.movieapp.ui.profile.watchhistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.mappers.WatchHistoryMapper
import com.karrar.movieapp.domain.usecase.GetWatchHistoryUseCase
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchHistoryViewModel @Inject constructor(
    private val getWatchHistoryUseCase: GetWatchHistoryUseCase,
    private val watchHistoryMapper: WatchHistoryMapper
) : ViewModel(), WatchHistoryInteractionListener {

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    private val _clickTVShowEvent = MutableLiveData<Event<Int>>()
    val clickTVShowEvent = _clickTVShowEvent.toLiveData()

    private val _uiState = MutableStateFlow(WatchHistoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getWatchHistoryData()
    }

    private fun getWatchHistoryData() {
        viewModelScope.launch {
            try {
                getWatchHistoryUseCase().collect { list ->
                    _uiState.update { watchHistoryUiState ->
                        watchHistoryUiState.copy(allMedia = list.map { watchHistoryMapper.map(it) })
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = listOf(Error(400, t.message.toString()))) }
            }

        }
    }

    override fun onClickMovie(item: MediaHistoryUiState) {
        if (item.mediaType == Constants.MOVIE) {
            _clickMovieEvent.postEvent(item.id)
        } else {
            _clickTVShowEvent.postEvent(item.id)
        }
    }

}