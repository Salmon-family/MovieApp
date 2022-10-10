package com.karrar.movieapp.data.remote.response


import com.google.gson.annotations.SerializedName

data class AddListResponse(
    @SerializedName("list_id")
    val listId: Int?,
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("success")
    val success: Boolean?
)