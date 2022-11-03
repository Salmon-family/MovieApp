package com.karrar.movieapp.utilities

object Constants {
    const val IMAGE_BASE_PATH = "https://image.tmdb.org/t/p/w780"
    const val MAX_NUMBER_AIRING_TODAY = 6
    const val MYSTERY_ID = 9648
    const val ADVENTURE_ID = 12
    const val MOVIE_CATEGORIES_ID = 1
    const val TV_CATEGORIES_ID = 2
    const val FIRST_CATEGORY_ID = 0
    const val ALL = "All"

    const val MAX_NUM_REVIEWS = 3

    const val MOVIE = "movie"
    const val TV_SHOWS = "tv"
    const val ACTOR = "person"
    const val PERSON = "person"
    const val ACTING = "Acting"
    const val NUM_HOME_REQUEST = 9
    const val  SUCCESS_REQUEST = 1
    const val INTERNET_STATUS = 400

    const val PROFILE = 1
    const val IMAGEACTORPATHWHENISNULL = "https://image.tmdb.org/t/p/w500null"
}

object DataStorePreferencesKeys {
    const val SESSION_ID_KEY = "session_id"
}

object ErrorUI {
    const val NEED_LOGIN = 100
    const val INTERNET_CONNECTION = 404

    const val NO_LOGIN = "NoLogin"
    const val EMPTY_FIELD = "EMPTY_FIELD"
    const val EMPTY_BODY = "EMPTY_BODY"
}
