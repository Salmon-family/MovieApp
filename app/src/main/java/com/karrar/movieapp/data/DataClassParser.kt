package com.karrar.movieapp.data

import com.google.gson.Gson
import javax.inject.Inject

class DataClassParser @Inject constructor(val gson: Gson) {
    inline fun <reified T> parseFromJson(json: String?, classType: Class<T>): T {
        return gson.fromJson(json,classType)
    }
}