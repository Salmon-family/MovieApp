package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.movieDetailsDto.reviews.ReviewsDto
import com.karrar.movieapp.domain.models.Review
import javax.inject.Inject


class ReviewMapper @Inject constructor(private val userMapper: UserMapper):Mapper<ReviewsDto, Review> {
    override fun map(input: ReviewsDto): Review {
        return Review(
            input.content,
            input.createdAt?.take(10),
            input.authorDetails?.let { userMapper.map(it) }
        )
    }
}