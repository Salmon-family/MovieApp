package com.karrar.movieapp.ui.ratedmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

@HiltViewModel
class RatedMoviesViewModel @Inject constructor(private val accountRepository: AccountRepository, private val movieRepository: MovieRepository) :
    ViewModel(), RatedMoviesInteractionListener {

    val ratedMovies = accountRepository.getSessionId().flatMapConcat { sessionId ->
        movieRepository.getRatedMovies(sessionId)
    }.asLiveData()
}