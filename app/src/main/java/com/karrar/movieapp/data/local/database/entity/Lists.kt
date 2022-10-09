package com.karrar.movieapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LISTS_TABLE")
class Lists(
    @PrimaryKey val listId: Int,
    var itemCount: Int,
    var listName: String,
    var isPublic: Boolean,
    var posterPath: String,
)