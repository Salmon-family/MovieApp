package com.karrar.movieapp.ui.profile.logout

sealed interface LogoutUIEvent {
    object LogoutEvent : LogoutUIEvent
    object CloseDialogEvent : LogoutUIEvent
}