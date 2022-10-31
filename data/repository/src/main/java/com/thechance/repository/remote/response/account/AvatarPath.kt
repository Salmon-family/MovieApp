package com.thechance.repository.remote.response.account


import com.google.gson.annotations.SerializedName

data class AvatarPath(
    @SerializedName("avatar_path")
    val avatarPath: String? = null
)