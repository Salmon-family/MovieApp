package com.karrar.movieapp.domain


sealed class LoginStatus {
    object Success : LoginStatus()
    data class Failure(val message: String) : LoginStatus()
}