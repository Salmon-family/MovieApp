package com.thechance.repository.local.database.daos

import androidx.room.*
import com.thechance.repository.local.database.entity.SearchHistoryEntity
import com.thechance.repository.local.database.entity.WatchHistoryEntity
import com.thechance.repository.local.database.entity.WatchList
import com.thechance.repository.local.database.entity.movie.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: WatchList)

    @Delete
    suspend fun delete(item: WatchList)

    @Query("SELECT * FROM WATCH_LIST_TABLE")
    fun getAllSavedMovies(): Flow<List<WatchList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search: SearchHistoryEntity)

    @Delete
    suspend fun delete(search: SearchHistoryEntity)

    @Query("SELECT * FROM SEARCH_HISTORY_TABLE")
    fun getAllSearchHistory(): Flow<List<SearchHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: WatchHistoryEntity)

    @Query("SELECT * FROM WATCH_HISTORY_TABLE")
    fun getAllWatchedMovies(): Flow<List<WatchHistoryEntity>>

    @Query("DELETE FROM WATCH_HISTORY_TABLE")
    suspend fun deleteAllWatchedMovies()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(items: List<PopularMovieEntity>)

    @Query("DELETE FROM POPULAR_MOVIE_TABLE")
    suspend fun deletePopularMovies()

    @Query("SELECT * FROM POPULAR_MOVIE_TABLE")
    fun getPopularMovies(): Flow<List<PopularMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMovie(items: List<TrendingMovieEntity>)

    @Query("DELETE FROM TRENDING_MOVIE_TABLE")
    suspend fun deleteAllTrendingMovies()

    @Query("SELECT * FROM TRENDING_MOVIE_TABLE")
    fun getTrendingMovies(): Flow<List<TrendingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowStreamingMovie(items: List<NowStreamingMovieEntity>)

    @Query("DELETE FROM NOW_STREAMING_MOVIE_TABLE")
    suspend fun deleteAllNowStreamingMovies()

    @Query("SELECT * FROM NOW_STREAMING_MOVIE_TABLE")
    fun getNowStreamingMovies(): Flow<List<NowStreamingMovieEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovie(items: List<UpcomingMovieEntity>)

    @Query("DELETE FROM UPCOMING_MOVIE_TABLE")
    suspend fun deleteAllUpcomingMovies()

    @Query("SELECT * FROM UPCOMING_MOVIE_TABLE")
    fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMysteryMovie(items: List<MysteryMovieEntity>)

    @Query("DELETE FROM MYSTERY_MOVIE_TABLE")
    suspend fun deleteAllMysteryMovies()

    @Query("SELECT * FROM MYSTERY_MOVIE_TABLE")
    fun getMysteryMovies(): Flow<List<MysteryMovieEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdventureMovie(items: List<AdventureMovieEntity>)

    @Query("DELETE FROM ADVENTURE_MOVIE_TABLE")
    suspend fun deleteAllAdventureMovies()

    @Query("SELECT * FROM ADVENTURE_MOVIE_TABLE")
    fun getAdventureMovies(): Flow<List<AdventureMovieEntity>>
}