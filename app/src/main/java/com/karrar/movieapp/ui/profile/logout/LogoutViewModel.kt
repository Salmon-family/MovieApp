package com.karrar.movieapp.ui.profile.logout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.repository.AccountRepository
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(private val accountRepository: AccountRepository) :
    ViewModel() {
    private val _clickLogoutEvent = MutableLiveData<Event<Boolean>>()
    val clickLoginEvent = _clickLogoutEvent.toLiveData()

    private val _closeDialogEvent = MutableLiveData<Event<Boolean>>()
    val closeDialogEvent = _closeDialogEvent.toLiveData()

    fun onLogout() {
        viewModelScope.launch {
            accountRepository.logout().collect {
                logoutEvent(it)
            }
        }
    }

    fun onCloseDialog() {
        _closeDialogEvent.postEvent(true)
    }

    private fun logoutEvent(state: UIState<Boolean>) {
        if (state is UIState.Success || state is UIState.Error) {
            _clickLogoutEvent.postEvent(true)
        }
    }
}