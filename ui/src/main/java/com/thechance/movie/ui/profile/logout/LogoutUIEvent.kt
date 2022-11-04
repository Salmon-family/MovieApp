package com.thechance.movie.ui.profile.logout

sealed interface LogoutUIEvent {
    object LogoutEvent : LogoutUIEvent
    object CloseDialogEvent : LogoutUIEvent
}