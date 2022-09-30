package com.karrar.movieapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCH_HISTORY_TABLE")
class SearchHistory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var search: String,
)