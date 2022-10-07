package com.karrar.movieapp.di

import com.google.gson.Gson
import com.karrar.movieapp.data.remote.AuthInterceptor
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.utilities.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMovieService(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): MovieService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(MovieService::class.java)
    }

//    @Singleton
//    @Provides
//    fun provideSeriesService(
//        client: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory,
//    ): SeriesService {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .client(client)
//            .addConverterFactory(gsonConverterFactory)
//            .build()
//
//        return retrofit.create(SeriesService::class.java)
//    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

}