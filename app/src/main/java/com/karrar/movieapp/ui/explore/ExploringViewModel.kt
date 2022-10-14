package com.karrar.movieapp.ui.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploringViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel(), TrendInteractionListener {

    private val _trend = MutableLiveData<UIState<List<Media>>>()
    val trend = _trend.toLiveData()

    private val _clickSearchEvent = MutableLiveData<Event<Boolean>>()
    var clickSearchEvent = _clickSearchEvent.toLiveData()

    private val _clickMoviesEvent = MutableLiveData<Event<Boolean>>()
    var clickMoviesEvent = _clickMoviesEvent.toLiveData()

    private val _clickSeriesEvent = MutableLiveData<Event<Boolean>>()
    var clickSeriesEvent = _clickSeriesEvent.toLiveData()

    private val _clickActorsEvent = MutableLiveData<Event<Boolean>>()
    var clickActorsEvent = _clickActorsEvent.toLiveData()

    private val _clickTrendEvent = MutableLiveData<Event<Int>>()
    var clickTrendEvent = _clickTrendEvent.toLiveData()

    val mediaType = MutableStateFlow("")

    init {
        getData()
    }

    override fun getData() {
        _trend.postValue(UIState.Loading)
        wrapWithState({
            val response = movieRepository.getDailyTrending()
            _trend.postValue(UIState.Success(response))
        }, {
            _trend.postValue(UIState.Error(""))
        })
    }

    override fun onClickTrend(trendID: Int, trendType: String) {
        _clickTrendEvent.postValue(Event(trendID))
        viewModelScope.launch { mediaType.emit(trendType) }
    }

    fun onClickSearch() {
        _clickSearchEvent.postEvent(true)
    }

    fun onClickMovies() {
        _clickMoviesEvent.postEvent(true)
    }

    fun onClickSeries() {
        _clickSeriesEvent.postEvent(true)
    }

    fun onClickActors() {
        _clickActorsEvent.postEvent(true)
    }

}