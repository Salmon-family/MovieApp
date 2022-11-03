package com.karrar.movieapp.ui.profile.logout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecases.LogoutUseCase
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase) :
    ViewModel() {

    private val _logoutUIEvent: MutableStateFlow<Event<LogoutUIEvent?>> = MutableStateFlow(Event(null))
    val logoutUIEvent= _logoutUIEvent.asStateFlow()
    fun onLogout() {
        viewModelScope.launch {
            logoutUseCase()
            logoutEvent()
        }
    }

    fun onCloseDialog() {
        _logoutUIEvent.update { Event(LogoutUIEvent.CloseDialogEvent) }
    }

    private fun logoutEvent() {
        _logoutUIEvent.update { Event(LogoutUIEvent.LogoutEvent) }
    }
}