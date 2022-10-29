package com.karrar.movieapp.ui.profile.logout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecase.LogoutUseCase
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase) :
    ViewModel() {
    private val _clickLogoutEvent = MutableLiveData<Event<Boolean>>()
    val clickLoginEvent = _clickLogoutEvent.toLiveData()

    private val _closeDialogEvent = MutableLiveData<Event<Boolean>>()
    val closeDialogEvent = _closeDialogEvent.toLiveData()

    fun onLogout() {
        viewModelScope.launch {
            logoutUseCase()
            logoutEvent()
        }
    }

    fun onCloseDialog() {
        _closeDialogEvent.postEvent(true)
    }

    private fun logoutEvent() {
        _clickLogoutEvent.postEvent(true)
    }
}