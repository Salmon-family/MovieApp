package com.thechance.repository.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCH_HISTORY_TABLE")
class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var search: String,
)