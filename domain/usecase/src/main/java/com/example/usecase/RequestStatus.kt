package com.example.usecase

sealed class RequestStatus {
    object Success : RequestStatus()
    data class Failure(val message: String) : RequestStatus()
}
