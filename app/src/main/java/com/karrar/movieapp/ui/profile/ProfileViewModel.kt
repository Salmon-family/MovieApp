package com.karrar.movieapp.ui.profile

import androidx.lifecycle.MutableLiveData
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.domain.models.Account
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
) : BaseViewModel() {

    private val _profileDetails = MutableLiveData<UIState<Account>>()
    val profileDetails = _profileDetails.toLiveData()

    private val _clickLoginEvent = MutableLiveData<Event<Boolean>>()
    val clickLoginEvent = _clickLoginEvent.toLiveData()

    private val _clickRatedMoviesEvent = MutableLiveData<Event<Boolean>>()
    val clickRatedMoviesEvent = _clickRatedMoviesEvent.toLiveData()

    private val _clickDialogLogoutEvent = MutableLiveData<Event<Boolean>>()
    val clickDialogLogoutEvent = _clickDialogLogoutEvent.toLiveData()

    private val _clickWatchHistoryEvent = MutableLiveData<Event<Boolean>>()
    val clickWatchHistoryEvent = _clickWatchHistoryEvent.toLiveData()


    init {
        getData()
    }

    override fun getData() {
        _profileDetails.postValue(UIState.Loading)
        wrapWithState({
            accountRepository.getSessionId().collect { sessionId ->
                val result = accountRepository.getAccountDetails(sessionId.toString())
                _profileDetails.value = UIState.Success(result)
                checkIfLogIn(sessionId)
            }
        }, {
            _profileDetails.value = UIState.Error(it.message.toString())
            checkTheError()
        })
    }

    private fun checkTheError() {
        if (_profileDetails.value  == UIState.Error("response is not successful"))
            _profileDetails.postValue(UIState.NoLogin)
    }

    private fun checkIfLogIn(sessionId: String?) {
        if (sessionId == "")
            _profileDetails.postValue(UIState.NoLogin)
    }


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