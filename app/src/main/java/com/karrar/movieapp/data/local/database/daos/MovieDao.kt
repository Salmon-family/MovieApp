package com.karrar.movieapp.data.local.database.daos

import androidx.room.*
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistory
import com.karrar.movieapp.data.local.database.entity.Lists
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Lists)

    @Delete
    suspend fun delete(item: Lists)

    @Update
    suspend fun update(list: Lists)

    @Query("SELECT * FROM LISTS_TABLE")
     fun getAllSavedLists(): Flow<List<Lists>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search: SearchHistoryEntity)

    @Delete
    suspend fun delete(search: SearchHistoryEntity)

    @Query("SELECT * FROM SEARCH_HISTORY_TABLE")
    fun getAllSearchHistory(): Flow<List<SearchHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: WatchHistory)

    @Delete
    suspend fun delete(item: WatchHistory)

    @Query("SELECT * FROM WATCH_HISTORY_TABLE")
    fun getAllWatchedMovies(): Flow<List<WatchHistory>>
}