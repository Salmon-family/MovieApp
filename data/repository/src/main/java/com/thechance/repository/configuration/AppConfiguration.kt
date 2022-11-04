package com.thechance.repository.configuration


interface AppConfiguration {

    fun getSessionId(): String?

    suspend fun saveSessionId(value: String)

    suspend fun saveRequestDate(key: String, value: Long)

    suspend fun getRequestDate(key: String): Long?

}

