package com.devfalah.usecases.home.mappers

import com.devfalah.usecases.mappers.Mapper
import com.thechance.remote.response.review.ReviewsDto
import com.devfalah.models.Review
import javax.inject.Inject

class ReviewMapper @Inject constructor() : Mapper<ReviewsDto, Review> {
    override fun map(input: ReviewsDto): Review {
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