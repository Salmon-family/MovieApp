package com.devfalah.usecases.home.mappers.search

import com.thechance.local.database.entity.SearchHistoryEntity
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.SearchHistory
import javax.inject.Inject

class SearchHistoryMapper @Inject constructor() :
    Mapper<SearchHistoryEntity, com.devfalah.models.SearchHistory> {
    override fun map(input: com.thechance.local.database.entity.SearchHistoryEntity): com.devfalah.models.SearchHistory {
        return com.devfalah.models.SearchHistory(
            id = input.id,
            name = input.search
        )
    }
}