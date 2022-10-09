package com.karrar.movieapp.ui.profile.watchhistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.MovieRepository
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
    val watchHistory = movieRepository.getAllWatchedMovies().asLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    override fun onClickMovie(movieID: Int) {
        _clickMovieEvent.postEvent(movieID)
    }

    fun clearHistory() {
        viewModelScope.launch {
            movieRepository.clearWatchHistory()
        }
    }
}