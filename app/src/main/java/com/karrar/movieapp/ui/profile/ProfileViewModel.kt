package com.karrar.movieapp.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.domain.models.Account
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        getProfileDetails()
    }

    private fun getProfileDetails(){
        _profileDetails.postValue(UIState.Loading)

        viewModelScope.launch {
            accountRepository.getSessionId().collect { sectionId ->
                wrapWithState({
                    val result = accountRepository.getAccountDetails(sectionId.toString())
                    _profileDetails.postValue(UIState.Success(result))
                }, {
                    _profileDetails.postValue(UIState.Error(it.message.toString()))
                })
            }
        }
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

    override fun getData() {
        TODO("Not yet implemented")
    }
}