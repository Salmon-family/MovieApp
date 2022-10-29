package com.karrar.movieapp.domain.home.refreshData

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.RequestStatus
import java.util.*
import javax.inject.Inject

class RefreshHomeDataOneTimePerDayUseCase @Inject constructor(
    private val refreshHomeDataUseCase: RefreshHomeDataUseCase,
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke(){
        val requestDate = movieRepository.getRequestDate()
            val currentDate = Date()
            if (requestDate != null) {
                val date = Date(requestDate)
                if (date.after(currentDate)) {
                    refreshHomeData(currentDate)
                }
            } else {
                refreshHomeData(currentDate)
            }
        }

    private suspend fun refreshHomeData(
        currentDate: Date,
    ) {
        when (refreshHomeDataUseCase()) {
            is RequestStatus.Failure -> throw Throwable("Error no found data")
            RequestStatus.Success -> movieRepository.saveRequestDate(currentDate.time)
        }
    }


}