package com.thechance.repository.local.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TRENDING_MOVIE_TABLE")
data class TrendingMovieEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
)
