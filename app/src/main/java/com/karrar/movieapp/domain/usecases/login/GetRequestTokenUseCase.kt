package com.karrar.movieapp.domain.usecases.login

import com.karrar.movieapp.data.repository.AccountRepository
import javax.inject.Inject

class GetRequestTokenUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke() : String{
        return accountRepository.getRequestToken()
    }
}