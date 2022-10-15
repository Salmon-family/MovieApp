package com.karrar.movieapp.di

import com.karrar.movieapp.data.local.LocalDataSource
import com.karrar.movieapp.data.local.LocalDataSourceImp
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
    abstract fun bindDataStore(dataStoreImp: LocalDataSourceImp) :LocalDataSource
}