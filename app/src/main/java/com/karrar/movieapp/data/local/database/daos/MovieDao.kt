package com.karrar.movieapp.data.local.database.daos

import androidx.room.*
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistory
import com.karrar.movieapp.data.local.database.entity.WatchList
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: WatchList)

    @Delete
    suspend fun delete(item: WatchList)

    @Query("SELECT * FROM WATCH_LIST_TABLE")
    fun getAllSavedMovies(): Flow<List<WatchList>>

    @Insert
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