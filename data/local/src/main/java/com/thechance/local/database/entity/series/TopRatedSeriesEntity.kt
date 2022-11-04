package com.thechance.local.database.entity.series

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TOP_RATED_SERIES_TABLE")
data class TopRatedSeriesEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
)