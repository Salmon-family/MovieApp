package com.karrar.movieapp.domain.usecases.login

import com.thechance.repository.AccountRepository
import javax.inject.Inject

class LoginWithUserNameAndPasswordUseCase @Inject constructor(
    private val accountRepository: com.thechance.repository.AccountRepository,
) {
    suspend operator fun invoke(userName: String, password: String): Boolean {
        return accountRepository.loginWithUserNameANdPassword(userName, password)
    }
}