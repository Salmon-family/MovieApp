package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.remote.response.PersonDto
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.SearchHistory
import javax.inject.Inject

class SearchHistoryMapper @Inject constructor() : Mapper<SearchHistoryEntity, SearchHistory> {
    override fun map(input: SearchHistoryEntity): SearchHistory {
        return SearchHistory(
            id = input.id,
            name = input.Search
        )
    }
}