package com.thechance.repository

import com.thechance.remote.response.account.AccountDto


interface AccountRepository {

    fun getSessionId(): String?

    suspend fun loginWithUserNameANdPassword(userName: String, password: String) : Boolean

    suspend fun logout()

    suspend fun getAccountDetails(): AccountDto?
}

