package com.thechance.movie.ui.movieDetails.mapper

import com.thechance.movie.ui.movieDetails.movieDetailsUIState.ReviewUIState
import javax.inject.Inject

class ReviewUIStateMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Review, ReviewUIState> {
    override fun map(input: com.devfalah.models.Review): ReviewUIState {
        return ReviewUIState(
            content = input.content,
            createDate = input.createDate,
            userImage = input.userImage,
            name = input.name,
            userName = input.userName,
            rating = input.rating
        )
    }
}