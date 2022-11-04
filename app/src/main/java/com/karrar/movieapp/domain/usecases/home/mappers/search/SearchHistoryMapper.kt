package com.karrar.movieapp.domain.usecases.home.mappers.search

import com.thechance.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.SearchHistory
import javax.inject.Inject

class SearchHistoryMapper @Inject constructor() : Mapper<SearchHistoryEntity, SearchHistory> {
    override fun map(input: com.thechance.local.database.entity.SearchHistoryEntity): SearchHistory {
        return SearchHistory(
            id = input.id,
            name = input.search
        )
    }
}