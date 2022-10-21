package com.karrar.movieapp.data.local.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NOW_STREAMING_MOVIE_TABLE")
data class NowStreamingMovieEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
)