package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Review
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.ReviewUIState
import javax.inject.Inject

class TvShowReviewUIMapper @Inject constructor() : Mapper<Review, ReviewUIState> {
    override fun map(input: Review): ReviewUIState {
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
