package com.thechance.viewmodel.profile.logout

sealed interface LogoutUIEvent {
    object LogoutEvent : LogoutUIEvent
    object CloseDialogEvent : LogoutUIEvent
}