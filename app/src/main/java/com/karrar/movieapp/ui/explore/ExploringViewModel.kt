package com.karrar.movieapp.ui.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecases.GetTrendingMovieUseCase
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.explore.exploreUIState.ErrorUIState
import com.karrar.movieapp.ui.explore.exploreUIState.ExploreUIState
import com.karrar.movieapp.ui.explore.exploreUIState.TrendyMediaUIState
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExploringViewModel @Inject constructor(
    private val getTrendyMovieUseCase: GetTrendingMovieUseCase,
    private val trendingUIStateMapper: TrendingUIStateMapper
) : BaseViewModel(), TrendInteractionListener {

    private val _uiState = MutableStateFlow(ExploreUIState())
    val uiState: StateFlow<ExploreUIState> = _uiState

    private val _clickSearchEvent = MutableLiveData<Event<Boolean>>()
    var clickSearchEvent = _clickSearchEvent.toLiveData()

    private val _clickMoviesEvent = MutableLiveData<Event<Boolean>>()
    var clickMoviesEvent = _clickMoviesEvent.toLiveData()

    private val _clickTVShowEvent = MutableLiveData<Event<Boolean>>()
    var clickTVShowEvent = _clickTVShowEvent.toLiveData()

    private val _clickActorsEvent = MutableLiveData<Event<Boolean>>()
    var clickActorsEvent = _clickActorsEvent.toLiveData()

    private val _clickTrendEvent = MutableLiveData<Event<TrendyMediaUIState>>()
    var clickTrendEvent = _clickTrendEvent.toLiveData()

    init {
        getData()
    }

    override fun getData() {
        _uiState.update { it.copy(isLoading = true, error = emptyList()) }
        viewModelScope.launch {
            try {
                val result = getTrendyMovieUseCase()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        trendyMovie = result.map { trendingUIStateMapper.map(it) })
                }
            } catch (e: Throwable) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = listOf(ErrorUIState(404, ""))
                    )
                }
            }
        }
    }

    override fun onClickTrend(item: TrendyMediaUIState) {
        _clickTrendEvent.postValue(Event(item))
    }

    fun onClickSearch() {
        _clickSearchEvent.postEvent(true)
    }

    fun onClickMovies() {
        _clickMoviesEvent.postEvent(true)
    }

    fun onClickTVShow() {
        _clickTVShowEvent.postEvent(true)
    }

    fun onClickActors() {
        _clickActorsEvent.postEvent(true)
    }

}