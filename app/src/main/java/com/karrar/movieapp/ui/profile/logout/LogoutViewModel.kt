package com.karrar.movieapp.ui.profile.logout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.usecase.LogoutUseCase
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase) :
    ViewModel() {

    val logoutUIEvent: MutableStateFlow<Event<LogoutUIEvent>?> = MutableStateFlow(null)

    fun onLogout() {
        viewModelScope.launch {
            logoutUseCase()
            logoutEvent()
        }
    }

    fun onCloseDialog() {
        logoutUIEvent.update { Event(LogoutUIEvent.CloseDialogEvent) }
    }

    private fun logoutEvent() {
        logoutUIEvent.update { Event(LogoutUIEvent.LogoutEvent) }
    }
}