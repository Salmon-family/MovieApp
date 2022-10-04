package com.karrar.movieapp.data.local.database.daos

import androidx.room.*
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: WatchList)

    @Delete
    suspend fun delete(list: WatchList)

    @Query("SELECT * FROM WATCH_LIST_TABLE")
    fun getAllSavedMovies(): Flow<List<WatchList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Search: SearchHistoryEntity)

    @Delete
    suspend fun delete(Search: SearchHistoryEntity)

    @Query("SELECT * FROM SEARCH_HISTORY_TABLE")
    fun getAllSearchHistory(): Flow<List<SearchHistoryEntity>>
}