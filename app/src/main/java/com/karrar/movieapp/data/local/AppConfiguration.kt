package com.karrar.movieapp.data.local


import javax.inject.Inject

interface AppConfiguration {

    fun readString(key: String): String?

    suspend fun writeString(key: String, value: String)


    suspend fun savePopularMoviesRequestDate(value: Long)

    suspend fun getPopularMoviesRequestDate(): Long?

    suspend fun saveTrendingMoviesRequestDate(value: Long)

    suspend fun getTrendingMoviesRequestDate(): Long?

    suspend fun saveUpcomingMoviesRequestDate(value: Long)

    suspend fun getUpcomingMoviesRequestDate(): Long?

    suspend fun saveAdventureMoviesRequestDate(value: Long)

    suspend fun getAdventureMoviesRequestDate(): Long?

    suspend fun saveMysteryMoviesRequestDate(value: Long)

    suspend fun getMysteryMoviesRequestDate(): Long?

    suspend fun saveNowStreamingMoviesRequestDate(value: Long)

    suspend fun getNowStreamingMoviesRequestDate(): Long?

    suspend fun saveOnTheAirSeriesRequestDate(value: Long)

    suspend fun getOnTheAirSeriesRequestDate(): Long?

    suspend fun saveAiringTodaySeriesRequestDate(value: Long)

    suspend fun getAiringTodaySeriesRequestDate(): Long?

    suspend fun saveActorsRequestDate(value: Long)

    suspend fun getActorsRequestDate(): Long?



}

class AppConfigurator @Inject constructor(private val dataStorePreferences: DataStorePreferences) :
    AppConfiguration {

    override fun readString(key: String): String? {
        return dataStorePreferences.readString(key)
    }

    override suspend fun writeString(key: String, value: String) {
        dataStorePreferences.writeString(key, value)
    }

    override suspend fun savePopularMoviesRequestDate(value: Long) {
        dataStorePreferences.writeLong(POPULAR_MOVIE_REQUEST_DATE_KEY, value)
    }

    override suspend fun getPopularMoviesRequestDate(): Long? {
        return dataStorePreferences.readLong(POPULAR_MOVIE_REQUEST_DATE_KEY)
    }

    override suspend fun saveTrendingMoviesRequestDate(value: Long) {
        dataStorePreferences.writeLong(TRENDING_MOVIE_REQUEST_DATE_KEY, value)
    }

    override suspend fun getTrendingMoviesRequestDate(): Long? {
        return dataStorePreferences.readLong(TRENDING_MOVIE_REQUEST_DATE_KEY)
    }

    override suspend fun saveUpcomingMoviesRequestDate(value: Long) {
        dataStorePreferences.writeLong(UPCOMING_MOVIE_REQUEST_DATE_KEY, value)
    }

    override suspend fun getUpcomingMoviesRequestDate(): Long? {
        return dataStorePreferences.readLong(UPCOMING_MOVIE_REQUEST_DATE_KEY)
    }

    override suspend fun saveAdventureMoviesRequestDate(value: Long) {
        dataStorePreferences.writeLong(ADVENTURE_MOVIE_REQUEST_DATE_KEY, value)
    }

    override suspend fun getAdventureMoviesRequestDate(): Long? {
        return dataStorePreferences.readLong(ADVENTURE_MOVIE_REQUEST_DATE_KEY)
    }

    override suspend fun saveMysteryMoviesRequestDate(value: Long) {
        dataStorePreferences.writeLong(MYSTERY_MOVIE_REQUEST_DATE_KEY, value)
    }

    override suspend fun getMysteryMoviesRequestDate(): Long? {
        return dataStorePreferences.readLong(MYSTERY_MOVIE_REQUEST_DATE_KEY)
    }

    override suspend fun saveNowStreamingMoviesRequestDate(value: Long) {
        dataStorePreferences.writeLong(MYSTERY_MOVIE_REQUEST_DATE_KEY, value)
    }

    override suspend fun getNowStreamingMoviesRequestDate(): Long? {
        return dataStorePreferences.readLong(NOW_STREAMING_MOVIE_REQUEST_DATE_KEY)
    }

    override suspend fun saveOnTheAirSeriesRequestDate(value: Long) {
        dataStorePreferences.writeLong(ON_THE_AIR_SERIES_REQUEST_DATE_KEY, value)
    }

    override suspend fun getOnTheAirSeriesRequestDate(): Long? {
        return dataStorePreferences.readLong(ON_THE_AIR_SERIES_REQUEST_DATE_KEY)
    }

    override suspend fun saveAiringTodaySeriesRequestDate(value: Long) {
        dataStorePreferences.writeLong(AIRING_TODAY_SERIES_REQUEST_DATE_KEY, value)
    }

    override suspend fun getAiringTodaySeriesRequestDate(): Long? {
        return dataStorePreferences.readLong(AIRING_TODAY_SERIES_REQUEST_DATE_KEY)
    }

    override suspend fun saveActorsRequestDate(value: Long) {
        dataStorePreferences.writeLong(ACTOR_REQUEST_DATE_KEY, value)
    }

    override suspend fun getActorsRequestDate(): Long? {
        return dataStorePreferences.readLong(ACTOR_REQUEST_DATE_KEY)
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
        const val ON_THE_AIR_SERIES_REQUEST_DATE_KEY = "on_the_air_series_request_date"
        const val AIRING_TODAY_SERIES_REQUEST_DATE_KEY = "airing_today_series_request_date"
    }
}