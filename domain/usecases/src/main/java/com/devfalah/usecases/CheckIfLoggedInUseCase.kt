package com.devfalah.usecases

import javax.inject.Inject

class CheckIfLoggedInUseCase @Inject constructor(private val accountRepository: com.thechance.repository.AccountRepository) {
    operator fun invoke() : Boolean{
        return !accountRepository.getSessionId().isNullOrBlank()
    }
}