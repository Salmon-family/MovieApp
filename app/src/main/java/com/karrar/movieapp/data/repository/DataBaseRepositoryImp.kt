package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.MovieDataBase
import com.karrar.movieapp.data.local.database.entity.Lists
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataBaseRepositoryImp @Inject constructor(
    private val movieDatabase: MovieDataBase
) : DatabaseRepository {
    override  fun getAllSavedLists(): Flow<List<Lists>> = movieDatabase.movieDao().getAllSavedLists()

    override suspend fun insertList(list: Lists) = movieDatabase.movieDao().insert(list)

    override suspend fun deleteList(list: Lists) = movieDatabase.movieDao().delete(list)

    override suspend fun updateList(list: Lists) = movieDatabase.movieDao().update(list)
}