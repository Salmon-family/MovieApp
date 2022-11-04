package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Review
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.ReviewUIState
import javax.inject.Inject

class TvShowReviewUIMapper @Inject constructor() :
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
