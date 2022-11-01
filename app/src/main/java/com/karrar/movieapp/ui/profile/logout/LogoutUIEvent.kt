package com.karrar.movieapp.ui.profile.logout

sealed class LogoutUIEvent {
    object LogoutEvent : LogoutUIEvent()
    object CloseDialogEvent : LogoutUIEvent()
}