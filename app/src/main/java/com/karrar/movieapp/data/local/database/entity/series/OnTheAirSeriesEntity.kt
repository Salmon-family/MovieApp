package com.karrar.movieapp.data.local.database.entity.series

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ON_THE_AIR_SERIES_TABLE")
data class OnTheAirSeriesEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
)