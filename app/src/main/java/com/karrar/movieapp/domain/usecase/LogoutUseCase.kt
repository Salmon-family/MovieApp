package com.karrar.movieapp.domain.usecase

import com.thechance.repository.AccountRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val accountRepository: com.thechance.repository.AccountRepository) {
    suspend operator fun invoke() {
        accountRepository.logout()
    }
}