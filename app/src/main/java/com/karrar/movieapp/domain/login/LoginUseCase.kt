package com.karrar.movieapp.domain.login

import com.karrar.movieapp.data.repository.AccountRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(userName: String,password: String) : Boolean{
        return accountRepository.loginWithUserNameANdPassword(userName,password)
    }
}