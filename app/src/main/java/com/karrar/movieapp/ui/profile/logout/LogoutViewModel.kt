package com.karrar.movieapp.ui.profile.logout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(private val accountRepository: AccountRepository) :
    ViewModel() {
    private val _clickLoginEvent = MutableLiveData<Event<Boolean>>()
    val clickLoginEvent = _clickLoginEvent.toLiveData()

    private val _closeDialogEvent = MutableLiveData<Event<Boolean>>()
    val closeDialogEvent = _closeDialogEvent.toLiveData()

    private val _requestState = MutableLiveData<State<Boolean>>()
    val requestState = _requestState.toLiveData()

    fun onLogout() {
        logout()
    }

    fun onCloseDialog() {
        _closeDialogEvent.postValue(Event(true))
    }

    private fun logout() {
        viewModelScope.launch {
            accountRepository.logout().collect {
                checkLogoutState(it)
            }
        }
    }

    private fun checkLogoutState(state: State<Boolean>) {
        when (state) {
            State.Loading -> _requestState.postValue(State.Loading)
            is State.Success -> _clickLoginEvent.postValue(Event(true))
            is State.Error -> _requestState.postValue(State.Error(state.message))
        }
    }
}