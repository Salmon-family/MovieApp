package com.example.usecase

sealed class LoginStatus {
    object Success : LoginStatus()
    data class Failure(val message: String) : LoginStatus()
}
