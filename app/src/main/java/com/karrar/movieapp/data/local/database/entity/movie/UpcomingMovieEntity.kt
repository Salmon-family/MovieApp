package com.karrar.movieapp.data.local.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UPCOMING_MOVIE_TABLE")
data class UpcomingMovieEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
)