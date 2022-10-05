package com.karrar.movieapp.utilities

import com.karrar.movieapp.data.remote.response.ListDetailsDto
import com.karrar.movieapp.data.remote.response.trailerVideosDto.ResultDto

fun String.checkIfGuest() =
    if (this == "") "Guest" else this

fun List<ResultDto?>.getKey(): String? =
    this.map {
        if (it?.type == "Trailer")
            return it.key
    }.toString()


fun ListDetailsDto.checkIfExist(movie_id: Int): Boolean {
    this.items?.map {
        if (it?.id == movie_id){
            return true
        }
    }
    return false
}