package com.karrar.movieapp.ui.profile.watchhistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchHistoryViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel(), WatchHistoryInteractionListener {

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    private val _clickTVShowEvent = MutableLiveData<Event<Int>>()
    val clickTVShowEvent = _clickTVShowEvent.toLiveData()

    private val _watchHistory = MutableLiveData<List<WatchHistoryEntity>>()
    val watchHistory = _watchHistory.toLiveData()

    override fun onClickMovie(mediaID: Int) {
        _watchHistory.value?.let { it ->
            val item = it.find { it.id == mediaID }
            item?.let {
                if (it.mediaType == Constants.MOVIE) {
                    _clickMovieEvent.postEvent(mediaID)
                } else {
                    _clickTVShowEvent.postEvent(mediaID)
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            val result = movieRepository.getAllWatchedMovies()
            _watchHistory.postValue(result)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            movieRepository.clearWatchHistory()
        }
    }
}