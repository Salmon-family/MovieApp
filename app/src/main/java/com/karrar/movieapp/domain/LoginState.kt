package com.karrar.movieapp.domain


sealed class LoginState {
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}