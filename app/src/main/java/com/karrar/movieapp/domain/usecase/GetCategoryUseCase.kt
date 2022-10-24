package com.karrar.movieapp.domain.usecase

import androidx.paging.PagingData
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(mediaType: Int, genreID: Int): Flow<PagingData<Media>> {
       return if (genreID == Constants.FIRST_CATEGORY_ID) {
            movieRepository.getAllMedia(mediaType)
        } else {
            movieRepository.getMediaByGenre(genreID, mediaType)
        }
    }
}