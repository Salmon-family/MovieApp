package com.karrar.movieapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository,
) :
    ViewModel() {

    val profileDetails = accountRepository.getSessionId().flatMapConcat { sessionId ->
        movieRepository.getAccountDetails(sessionId.toString())
    }.asLiveData()

    private val _navigateToRatedMovies = MutableLiveData<Event<Boolean>>()
    val navigateToRatedMovies: LiveData<Event<Boolean>> = _navigateToRatedMovies

    private val _navigateToDialogLogout = MutableLiveData<Event<Boolean>>()
    val navigateToDialogLogout: LiveData<Event<Boolean>> = _navigateToDialogLogout

    private val _navigateToWatchHistory = MutableLiveData<Event<Boolean>>()
    val navigateToWatchHistory: LiveData<Event<Boolean>> = _navigateToWatchHistory


    fun onNavigateToRatedMovies() {
        _navigateToRatedMovies.postValue(Event(true))
    }

    fun onNavigateToDialogLogout() {
        _navigateToDialogLogout.postValue(Event(true))
    }

    fun onNavigateToWatchHistory() {
        _navigateToWatchHistory.postValue(Event(true))
    }
}