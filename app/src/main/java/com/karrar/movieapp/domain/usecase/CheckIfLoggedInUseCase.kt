package com.karrar.movieapp.domain.usecase

import com.thechance.repository.AccountRepository
import javax.inject.Inject

class CheckIfLoggedInUseCase @Inject constructor(private val accountRepository: com.thechance.repository.AccountRepository) {
    operator fun invoke() : Boolean{
        return !accountRepository.getSessionId().isNullOrBlank()
    }
}