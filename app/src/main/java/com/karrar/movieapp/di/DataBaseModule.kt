package com.karrar.movieapp.di

import android.content.Context
import androidx.room.Room
import com.karrar.movieapp.data.database.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    private lateinit var movieDatabase: MovieDataBase

    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): MovieDataBase =
        Room.databaseBuilder(context,MovieDataBase::class.java,"MovieDatabase").build()
}