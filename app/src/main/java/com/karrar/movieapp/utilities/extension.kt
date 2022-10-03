package com.karrar.movieapp.utilities

import com.karrar.movieapp.data.remote.response.trailerVideosDto.ResultDto

fun String.checkIfGuest() =
    if (this == "") "Guest" else this

fun List<ResultDto?>.getKey(): String? =
    this.forEach {
        if (it?.type == "Trailer")
            return it.key
    }.toString()