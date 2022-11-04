package com.devfalah.usecases

import com.thechance.repository.AccountRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke() {
        accountRepository.logout()
    }
}