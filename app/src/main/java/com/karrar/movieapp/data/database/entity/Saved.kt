package com.karrar.movieapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SAVED_MOVIES_TABLE")
class Saved(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var movieId: Int,
    var movieName: String,
    var posterPath: String,
)