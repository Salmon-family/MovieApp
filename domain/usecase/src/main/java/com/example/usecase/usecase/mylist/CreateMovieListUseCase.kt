package com.example.usecase.usecase.mylist
import com.example.models.models.CreatedList
import com.example.usecase.utilites.ErrorUI
import com.karrar.movieapp.domain.usecase.mylist.GetMyListUseCase
import javax.inject.Inject

class CreateMovieListUseCase @Inject constructor(
    private val accountRepository: com.thechance.repository.AccountRepository,
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val getMyListUseCase: GetMyListUseCase
) {

    suspend operator fun invoke(listName: String): List<CreatedList> {
        val sessionId = accountRepository.getSessionId()
        return sessionId?.let {
            val item = movieRepository.createList(it, listName)
            if (item?.success == true) {
                getMyListUseCase()
            } else {
                throw Throwable(ErrorUI.EMPTY_BODY)
            }
        } ?: throw Throwable(ErrorUI.NO_LOGIN)
    }


}