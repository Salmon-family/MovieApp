package com.karrar.movieapp.di

import com.karrar.movieapp.data.local.DataStore
import com.karrar.movieapp.data.local.DataStoreImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {


    @Singleton
    @Binds
    abstract fun bindDataStore(dataStoreImp: DataStoreImp) :DataStore
}