package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.entity.Lists
  import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun getAllSavedLists(): Flow<List<Lists>>
    suspend fun insertList(list : Lists)
    suspend fun deleteList(list: Lists)
    suspend fun updateList(list: Lists)
}