package com.karrar.movieapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.entity.WatchList
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity

@Database(entities = [WatchList::class, SearchHistoryEntity::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private const val DATABASE_NAME = "MovieDatabase"

        @Volatile
        private var INSTANCE: MovieDataBase? = null

        fun getInstance(context: Context): MovieDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDataBase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}