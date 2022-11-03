package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.data.repository.AccountRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke() {
        accountRepository.logout()
    }
}