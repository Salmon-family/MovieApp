package com.example.usecase.mappers.search

import com.example.models.models.SearchHistory
import com.example.usecase.mappers.Mapper
import com.thechance.repository.local.database.entity.SearchHistoryEntity
import javax.inject.Inject

class SearchHistoryMapper @Inject constructor() : Mapper<SearchHistoryEntity, SearchHistory> {
    override fun map(input: SearchHistoryEntity): SearchHistory {
        return SearchHistory(
            id = input.id,
            name = input.search
        )
    }
}
