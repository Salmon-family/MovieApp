package com.thechance.repository.di

import com.thechance.repository.local.AppConfiguration
import com.thechance.repository.local.AppConfigurator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppConfigurationModule {


    @Singleton
    @Binds
    abstract fun bindAppConfiguration(appConfigurator: AppConfigurator) : AppConfiguration
}