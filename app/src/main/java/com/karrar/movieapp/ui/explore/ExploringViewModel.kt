package com.karrar.movieapp.ui.explore

import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecases.GetTrendingMovieUseCase
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.explore.exploreUIState.ErrorUIState
import com.karrar.movieapp.ui.explore.exploreUIState.ExploreUIState
import com.karrar.movieapp.ui.explore.exploreUIState.ExploringUIEvent
import com.karrar.movieapp.ui.explore.exploreUIState.TrendyMediaUIState
import com.karrar.movieapp.utilities.Event
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

    val exploringUIEvent: MutableStateFlow<Event<ExploringUIEvent>?> = MutableStateFlow(null)

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
        exploringUIEvent.update { Event(ExploringUIEvent.TrendEvent(item)) }
    }

    fun onClickSearch() {
        exploringUIEvent.update { Event(ExploringUIEvent.SearchEvent) }
    }

    fun onClickMovies() {
        exploringUIEvent.update { Event(ExploringUIEvent.MoviesEvent) }
    }

    fun onClickTVShow() {
        exploringUIEvent.update { Event(ExploringUIEvent.TVShowEvent) }
    }

    fun onClickActors() {
        exploringUIEvent.update { Event(ExploringUIEvent.ActorsEvent) }
    }

}