package com.example.usecase.utilites

import com.thechance.repository.remote.response.MyListsDto
import com.thechance.repository.remote.response.trailerVideosDto.ResultDto
import java.text.SimpleDateFormat
import java.util.*

fun List<ResultDto?>.getKey(): String? =
    this.map {
        if (it?.type == "Trailer") {
            return it.key
        }
    }.toString()

fun <T> List<T>.margeTowList(secondList: List<T>): List<T> {
    return this.plus(secondList)
}

fun MyListsDto.checkIfExist(movie_id: Int): Boolean {
    this.items?.map {
        if (it.id == movie_id) {
            return true
        }
    }
    return false
}

fun Date.convertToDayMonthYearFormat(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(this)
}
