package com.thechance.repository.local.database.entity.series

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AIRING_TODAY_SERIES_TABLE")
data class AiringTodaySeriesEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
)