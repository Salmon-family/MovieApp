package com.karrar.movieapp.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.MediaInfo
import com.karrar.movieapp.domain.models.Trend
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploringViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel(), TrendInteractionListener{
    private val _trend = MutableLiveData<State<List<Trend>>>()
    val trend: LiveData<State<List<Trend>>> get() = _trend

    private val _clickSearchEvent = MutableLiveData<Event<Boolean>>()
    var clickSearchEvent: LiveData<Event<Boolean>> = _clickSearchEvent

    private val _clickMoviesEvent = MutableLiveData<Event<Boolean>>()
    var clickMoviesEvent: LiveData<Event<Boolean>> = _clickMoviesEvent

    private val _clickSeriesEvent = MutableLiveData<Event<Boolean>>()
    var clickSeriesEvent: LiveData<Event<Boolean>> = _clickSeriesEvent

    private val _clickActorsEvent = MutableLiveData<Event<Boolean>>()
    var clickActorsEvent: LiveData<Event<Boolean>> = _clickActorsEvent

    private val _clickTrendEvent = MutableLiveData<Event<Int>>()
    var clickTrendEvent: LiveData<Event<Int>> = _clickTrendEvent

    val mediaType = MutableStateFlow("")

    init {
        getDailyTrending()
    }

    private fun getDailyTrending(){
        viewModelScope.launch {
            movieRepository.getDailyTrending().collect{
                _trend.postValue(it)
            }
        }
    }

    override fun onClickTrend(trendID: Int, trendType: String) {
        _clickTrendEvent.postValue(Event(trendID))
        viewModelScope.launch { mediaType.emit(trendType) }
    }

    fun onClickSearch(){
        _clickSearchEvent.postValue(Event(true))
    }

    fun onClickMovies(){
        _clickMoviesEvent.postValue(Event(true))
    }

    fun onClickSeries(){
        _clickSeriesEvent.postValue(Event(true))
    }

    fun onClickActors(){
        _clickActorsEvent.postValue(Event(true))
    }
}