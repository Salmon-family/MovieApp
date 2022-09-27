package com.karrar.movieapp.data.database

import androidx.room.*
import com.karrar.movieapp.data.database.entity.WatchList
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: WatchList)

    @Delete
    suspend fun delete(list: WatchList)

    @Query("SELECT * FROM WATCH_LIST_TABLE")
    fun getAllSavedMovies(): Flow<List<WatchList>>
}