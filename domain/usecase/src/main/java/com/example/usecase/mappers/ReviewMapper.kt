package com.example.usecase.mappers

import com.example.models.models.Review
import com.example.models.models.User
import com.example.usecase.mappers.account.UserMapper
import com.thechance.repository.remote.response.review.ReviewsDto
import javax.inject.Inject

class ReviewMapper @Inject constructor(private val userMapper: UserMapper) :
    Mapper<ReviewsDto, Review> {
    override fun map(input: ReviewsDto): Review {
        return Review(
            input.content ?: "",
            input.createdAt?.take(10) ?: "",
            input.authorDetails?.let { userMapper.map(it) }
                ?: User("", "Guest", "Guest", 0F)
        )
    }
}
