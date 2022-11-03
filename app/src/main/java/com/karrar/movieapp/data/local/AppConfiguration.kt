package com.karrar.movieapp.data.local


import javax.inject.Inject

interface AppConfiguration {

    fun readString(key: String): String?

    suspend fun writeString(key: String, value: String)


    suspend fun saveRequestDate(key: String,value: Long)

    suspend fun getRequestDate(key: String): Long?

}

class AppConfigurator @Inject constructor(private val dataStorePreferences: DataStorePreferences) :
    AppConfiguration {

    override fun readString(key: String): String? {
        return dataStorePreferences.readString(key)
    }

    override suspend fun writeString(key: String, value: String) {
        dataStorePreferences.writeString(key, value)
    }

    override suspend fun saveRequestDate(key: String, value: Long) {
        dataStorePreferences.writeLong(key, value)
    }

    override suspend fun getRequestDate(key: String): Long? {
        return dataStorePreferences.readLong(key)
    }



    companion object DataStorePreferencesKeys {
        const val SESSION_ID_KEY = "session_id"
        const val ACTOR_REQUEST_DATE_KEY = "actor_request_date"
        const val POPULAR_MOVIE_REQUEST_DATE_KEY = "popular_movie_request_date"
        const val TRENDING_MOVIE_REQUEST_DATE_KEY = "trending_movie_request_date"
        const val UPCOMING_MOVIE_REQUEST_DATE_KEY = "upcoming_movie_request_date"
        const val ADVENTURE_MOVIE_REQUEST_DATE_KEY = "adventure_movie_request_date"
        const val NOW_STREAMING_MOVIE_REQUEST_DATE_KEY = "now_streaming_movie_request_date"
        const val MYSTERY_MOVIE_REQUEST_DATE_KEY = "mystery_movie_request_date"
        const val TOP_RATED_SERIES_REQUEST_DATE_KEY = "top_rated_series_request_date"
        const val ON_THE_AIR_SERIES_REQUEST_DATE_KEY = "on_the_air_series_request_date"
        const val AIRING_TODAY_SERIES_REQUEST_DATE_KEY = "airing_today_series_request_date"
    }
}