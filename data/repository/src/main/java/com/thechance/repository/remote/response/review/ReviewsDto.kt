package com.thechance.repository.remote.response.review

import com.google.gson.annotations.SerializedName

data class ReviewsDto(
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("author_details")
    val authorDetails: AuthorDetailsDto? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("url")
    val url: String? = null
)