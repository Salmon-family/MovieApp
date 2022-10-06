package com.karrar.movieapp.di

import android.content.Context
import androidx.room.Room
import com.karrar.movieapp.data.local.DataStorePreferences
import com.karrar.movieapp.data.local.database.MovieDataBase
import com.karrar.movieapp.data.local.database.daos.MovieDao
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
    fun providesRoomDatabase(@ApplicationContext context: Context): MovieDataBase =
        Room.databaseBuilder(context, MovieDataBase::class.java, "MovieDatabase").build()

    @Singleton
    @Provides
    fun provideMovieDao(@ApplicationContext context: Context): MovieDao{
        return providesRoomDatabase(context).movieDao()
    }

    @Singleton
    @Provides
    fun provideDataStorePreferences(@ApplicationContext context: Context) =
        DataStorePreferences(context)
}