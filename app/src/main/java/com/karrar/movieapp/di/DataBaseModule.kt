package com.karrar.movieapp.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.karrar.movieapp.data.local.DataStorePreferences
import com.karrar.movieapp.data.local.database.Converters
import com.karrar.movieapp.data.local.database.MovieDataBase
import com.karrar.movieapp.data.local.database.daos.ActorDao
import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.local.database.daos.SeriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context,
        converters: Converters,
    ): MovieDataBase =
        Room.databaseBuilder(context, MovieDataBase::class.java, "MovieDatabase")
            .addTypeConverter(converters)
            .build()

    @Singleton
    @Provides
    fun provideMovieDao(movieDataBase: MovieDataBase): MovieDao {
        return movieDataBase.movieDao()
    }

    @Singleton
    @Provides
    fun provideActorDao(movieDataBase: MovieDataBase): ActorDao {
        return movieDataBase.actorDao()
    }

    @Singleton
    @Provides
    fun provideSeriesDao(movieDataBase: MovieDataBase): SeriesDao {
        return movieDataBase.seriesDao()
    }

    @Singleton
    @Provides
    fun provideConverters(gson: Gson): Converters {
        return Converters(gson)
    }

    @Singleton
    @Provides
    fun provideDataStorePreferences(@ApplicationContext context: Context) =
        DataStorePreferences(context)
}