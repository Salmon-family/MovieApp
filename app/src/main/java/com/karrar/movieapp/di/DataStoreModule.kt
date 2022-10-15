package com.karrar.movieapp.di

import com.karrar.movieapp.data.local.LocalDataStore
import com.karrar.movieapp.data.local.LocalDataStoreImp
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
    abstract fun bindDataStore(dataStoreImp: LocalDataStoreImp) :LocalDataStore
}