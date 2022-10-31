package com.thechance.repository.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thechance.repository.local.database.entity.ActorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActorDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(items: List<ActorEntity>)

    @Query("DELETE FROM ACTOR_TABLE")
    suspend fun deleteActors()

    @Query("SELECT * FROM ACTOR_TABLE")
    fun getActors(): Flow<List<ActorEntity>>
}