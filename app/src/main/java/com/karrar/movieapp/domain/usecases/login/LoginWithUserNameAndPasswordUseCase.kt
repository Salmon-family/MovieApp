package com.karrar.movieapp.domain.usecases.login

import com.karrar.movieapp.data.repository.AccountRepository
import javax.inject.Inject

class LoginWithUserNameAndPasswordUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(userName: String, password: String): Boolean {
        return accountRepository.loginWithUserNameANdPassword(userName, password)
    }
}