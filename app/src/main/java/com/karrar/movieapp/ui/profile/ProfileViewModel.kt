package com.karrar.movieapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karrar.movieapp.data.remote.repository.AccountRepository
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository,
) :
    ViewModel() {
    @ExperimentalCoroutinesApi
    val profileDetails = accountRepository.getSessionId().flatMapLatest { sessionId ->
        movieRepository.getAccountDetails(sessionId)
    }.asLiveData()

    private val _navigateToRatedMovies = MutableLiveData<Event<Boolean>>()
    val navigateToRatedMovies: LiveData<Event<Boolean>> = _navigateToRatedMovies

    private val _navigateToDialogLogout = MutableLiveData<Event<Boolean>>()
    val navigateToDialogLogout: LiveData<Event<Boolean>> = _navigateToDialogLogout


    fun onNavigateToRatedMovies() {
        _navigateToRatedMovies.postValue(Event(true))
    }

    fun onNavigateToDialogLogout() {
        _navigateToDialogLogout.postValue(Event(true))
    }
}