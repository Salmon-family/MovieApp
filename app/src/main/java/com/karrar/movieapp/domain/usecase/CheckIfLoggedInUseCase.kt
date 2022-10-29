package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.AccountRepository
import javax.inject.Inject

class CheckIfLoggedInUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    operator fun invoke() : Boolean{
        return !accountRepository.getSessionId().isNullOrBlank()
    }
}