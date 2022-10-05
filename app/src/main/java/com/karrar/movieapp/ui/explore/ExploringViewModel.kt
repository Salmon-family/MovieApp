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

    val searchText = MutableStateFlow("")

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

    }
}