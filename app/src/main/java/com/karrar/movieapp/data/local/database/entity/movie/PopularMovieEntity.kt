package com.karrar.movieapp.data.local.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "POPULAR_MOVIE_TABLE")
data class PopularMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
    val movieRate: Double,
    val genre: List<String>,
)