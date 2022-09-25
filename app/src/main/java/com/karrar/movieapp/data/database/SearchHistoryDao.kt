package com.karrar.movieapp.data.database

import androidx.room.*
import com.karrar.movieapp.data.database.entity.SearchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Insert
     suspend fun insert(Search: SearchHistory)

    @Delete
    suspend fun delete(Search: SearchHistory)

    @Query("SELECT * FROM SEARCH_HISTORY_TABLE")
    fun getAllSearchHistory(): Flow<List<SearchHistory>>

}
