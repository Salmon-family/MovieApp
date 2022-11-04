package com.thechance.local.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import javax.inject.Inject

@ProvidedTypeConverter
class Converters @Inject constructor(val gson : Gson) {

    @TypeConverter
    fun listToJson(value: List<String>): String = gson.toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = gson.fromJson(value, Array<String>::class.java).toList()
}