package com.thechance.repository.local.database.entity.movie


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ADVENTURE_MOVIE_TABLE")
data class AdventureMovieEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
)
