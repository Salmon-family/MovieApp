package com.thechance.viewmodel.tvShowDetails.tvShowUIMapper

import com.devfalah.models.Review
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.tvShowDetails.tvShowUIState.ReviewUIState
import javax.inject.Inject

class TvShowReviewUIMapper @Inject constructor() :
    Mapper<Review, ReviewUIState> {
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
