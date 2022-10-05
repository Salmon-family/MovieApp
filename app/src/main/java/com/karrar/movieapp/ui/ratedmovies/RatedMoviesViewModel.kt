package com.karrar.movieapp.ui.ratedmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.repository.AccountRepository
import com.karrar.movieapp.data.remote.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class RatedMoviesViewModel @Inject constructor(private val accountRepository: AccountRepository, private val movieRepository: MovieRepository) :
    ViewModel(), RatedMoviesInteractionListener {

    @OptIn(ExperimentalCoroutinesApi::class)
    val ratedMovies = accountRepository.getSessionId().flatMapLatest { sessionId ->
        movieRepository.getRatedMovies(sessionId)
    }.asLiveData()
}