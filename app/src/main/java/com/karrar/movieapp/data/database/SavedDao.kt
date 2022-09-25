package com.karrar.movieapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.karrar.movieapp.data.database.entity.Saved
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedDao {
    @Insert
    suspend fun insert(movie: Saved)

    @Delete
    suspend fun delete(movie: Saved)

    @Query("SELECT * FROM SAVED_MOVIES_TABLE")
    fun getAllSavedMovies(): Flow<List<Saved>>
}