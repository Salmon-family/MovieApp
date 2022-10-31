package com.thechance.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thechance.repository.local.database.daos.ActorDao
import com.thechance.repository.local.database.daos.MovieDao
import com.thechance.repository.local.database.daos.SeriesDao
import com.thechance.repository.local.database.entity.ActorEntity
import com.thechance.repository.local.database.entity.SearchHistoryEntity
import com.thechance.repository.local.database.entity.WatchHistoryEntity
import com.thechance.repository.local.database.entity.WatchList
import com.thechance.repository.local.database.entity.movie.*
import com.thechance.repository.local.database.entity.series.AiringTodaySeriesEntity
import com.thechance.repository.local.database.entity.series.OnTheAirSeriesEntity
import com.thechance.repository.local.database.entity.series.TopRatedSeriesEntity

@Database(
    entities = [WatchList::class, SearchHistoryEntity::class, WatchHistoryEntity::class, PopularMovieEntity::class,
        ActorEntity::class, TrendingMovieEntity::class, NowStreamingMovieEntity::class, UpcomingMovieEntity::class,
        MysteryMovieEntity::class, AdventureMovieEntity::class, AiringTodaySeriesEntity::class,
        OnTheAirSeriesEntity::class, TopRatedSeriesEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun seriesDao(): SeriesDao
}