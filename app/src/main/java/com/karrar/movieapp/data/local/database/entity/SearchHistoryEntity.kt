package com.karrar.movieapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCH_HISTORY_TABLE")
class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var Search: String,
)