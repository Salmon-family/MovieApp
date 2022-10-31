package com.karrar.movieapp.domain.mappers.search

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.SearchHistory
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