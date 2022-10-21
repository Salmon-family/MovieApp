package com.karrar.movieapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karrar.movieapp.data.local.database.entity.series.AiringTodaySeriesEntity
import com.karrar.movieapp.data.local.database.entity.series.OnTheAirSeriesEntity
import com.karrar.movieapp.data.local.database.entity.series.TopRatedSeriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeriesDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOnTheAirSeries(items: List<OnTheAirSeriesEntity>)

    @Query("DELETE FROM ON_THE_AIR_SERIES_TABLE")
    suspend fun deleteAllOnTheAirSeries()

    @Query("SELECT * FROM ON_THE_AIR_SERIES_TABLE")
    fun getOnTheAirSeries(): Flow<List<OnTheAirSeriesEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAiringTodaySeries(items: List<AiringTodaySeriesEntity>)

    @Query("DELETE FROM AIRING_TODAY_SERIES_TABLE")
    suspend fun deleteAllAiringTodaySeries()

    @Query("SELECT * FROM AIRING_TODAY_SERIES_TABLE")
    fun getAiringTodaySeries(): Flow<List<AiringTodaySeriesEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedSeries(items: List<TopRatedSeriesEntity>)

    @Query("DELETE FROM TOP_RATED_SERIES_TABLE")
    suspend fun deleteAllTopRatedSeries()

    @Query("SELECT * FROM TOP_RATED_SERIES_TABLE")
    fun getTopRatedSeries(): Flow<List<TopRatedSeriesEntity>>
}