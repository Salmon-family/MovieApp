package com.karrar.movieapp.ui.logout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.AccountRepository
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(private val accountRepository: AccountRepository) :
    ViewModel() {
    private val _navigateToLogin = MutableLiveData<Event<Boolean>>()
    val navigateToLogin: LiveData<Event<Boolean>> = _navigateToLogin

    private val _closeDialog = MutableLiveData<Event<Boolean>>()
    val closeDialog: LiveData<Event<Boolean>> = _closeDialog

    private val _requestState = MutableLiveData<State<Boolean>>()
    val requestState: LiveData<State<Boolean>> = _requestState

    fun onLogout() {
        logout()
    }

    fun onCloseDialog() {
        _closeDialog.postValue(Event(true))
    }

    private fun logout() {
        viewModelScope.launch {
            accountRepository.logout().collect {
                when (it) {
                    State.Loading -> _requestState.postValue(State.Loading)
                    is State.Success -> _navigateToLogin.postValue(Event(true))
                    is State.Error -> _requestState.postValue(State.Error(it.message))
                }
            }
        }
    }
}