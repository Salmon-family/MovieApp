package com.karrar.movieapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ACTOR_TABLE")
data class ActorEntity(
    @PrimaryKey val id : Int,
    val name : String,
    val imageUrl : String,
)