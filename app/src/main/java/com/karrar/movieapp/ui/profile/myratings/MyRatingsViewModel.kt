package com.karrar.movieapp.ui.profile.myratings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

@HiltViewModel
class MyRatingsViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository,
) :
    ViewModel(), RatedMoviesInteractionListener {

    val ratedMovies = accountRepository.getSessionId().flatMapConcat { sessionId ->
        movieRepository.getRatedMovies(sessionId)
    }.asLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    override fun onClickMovie(movieId: Int) {
        _clickMovieEvent.postEvent(movieId)
    }
}