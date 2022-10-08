package com.karrar.movieapp.ui.watchhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchHistoryViewModel @Inject constructor(
    movieRepository: MovieRepository,
) : ViewModel(), WatchHistoryInteractionListener {
    val watchHistory = movieRepository.getAllWatchedMovies().asLiveData()

    override fun onClickMovie(movieID: Int) {

    }
}