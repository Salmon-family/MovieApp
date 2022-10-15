package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.review.ReviewsDto
import com.karrar.movieapp.domain.mappers.account.UserMapper
import com.karrar.movieapp.domain.models.Review
import com.karrar.movieapp.domain.models.User
import javax.inject.Inject


class ReviewMapper @Inject constructor(private val userMapper: UserMapper):Mapper<ReviewsDto, Review> {
    override fun map(input: ReviewsDto): Review {
        return Review(
            input.content?:"",
            input.createdAt?.take(10)?: "",
            input.authorDetails?.let { userMapper.map(it) }
                ?: User("","Guest","Guest",0F)
        )
    }
}