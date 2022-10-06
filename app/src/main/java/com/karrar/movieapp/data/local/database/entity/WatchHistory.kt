package com.karrar.movieapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WATCH_HISTORY_TABLE")
class WatchHistory(
    @PrimaryKey(autoGenerate = false) val id: Long?,
    var posterPath: String?,
    var movieTitle: String?,
    var voteAverage: Float?,
    var releaseDate: String?,
    var movieDuration: String?
)