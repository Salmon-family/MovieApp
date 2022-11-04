package com.karrar.movieapp.domain.usecases.home.mappers

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.thechance.remote.response.review.ReviewsDto
import com.karrar.movieapp.domain.models.Review
import javax.inject.Inject

class ReviewMapper @Inject constructor() : Mapper<ReviewsDto, Review> {
    override fun map(input: com.thechance.remote.response.review.ReviewsDto): Review {
        return Review(
            content = input.content ?: "",
            createDate = input.createdAt?.take(10) ?: "",
            userImage = input.authorDetails?.avatarPath ?: "",
            name = input.authorDetails?.name ?: "GUEST",
            userName = input.authorDetails?.username ?: "GUEST",
            rating = input.authorDetails?.rating?.toFloat() ?: 0f
        )
    }
}