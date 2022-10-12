package com.karrar.movieapp.ui.profile

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
class ProfileViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val movieRepository: MovieRepository,
) :
    ViewModel() {

    val profileDetails = accountRepository.getSessionId().flatMapConcat { sessionId ->
        movieRepository.getAccountDetails(sessionId.toString())
    }.asLiveData()

    private val _clickLoginEvent = MutableLiveData<Event<Boolean>>()
    val clickLoginEvent = _clickLoginEvent.toLiveData()

    private val _clickRatedMoviesEvent = MutableLiveData<Event<Boolean>>()
    val clickRatedMoviesEvent = _clickRatedMoviesEvent.toLiveData()

    private val _clickDialogLogoutEvent = MutableLiveData<Event<Boolean>>()
    val clickDialogLogoutEvent = _clickDialogLogoutEvent.toLiveData()

    private val _clickWatchHistoryEvent = MutableLiveData<Event<Boolean>>()
    val clickWatchHistoryEvent = _clickWatchHistoryEvent.toLiveData()


    fun onClickRatedMovies() {
        _clickRatedMoviesEvent.postEvent(true)
    }

    fun onClickLogout() {
        _clickDialogLogoutEvent.postEvent(true)
    }

    fun onClickWatchHistory() {
        _clickWatchHistoryEvent.postEvent(true)
    }

    fun onClickLogin() {
        _clickLoginEvent.postEvent(true)
    }
}