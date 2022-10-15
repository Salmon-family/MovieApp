package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.ListItem
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ItemListMapperTest {

    private lateinit var itemListMapper: ItemListMapper

    @BeforeAll
    fun setUp() {
        itemListMapper = ItemListMapper()
    }

    @Test
    fun should_ReturnItemListMapper_when_EnterListItem() {
        // given a List Item object with random values
        val listsItem = ListItem(
            adult = false,
            backdropPath = "backdropPath",
            genreIds = listOf(1, 2, 3),
            id = 1,
            mediaType = "mediaType",
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            releaseDate = "rele",
            title = "title",
            video = true,
            voteAverage = 1.0,
            voteCount = 1
        )

        // when map is called
        val result = itemListMapper.map(listsItem)

        // then the result should be a genre object with the same values
        assertEquals(listsItem.id, result.mediaID)
        assertEquals(
            BuildConfig.IMAGE_BASE_PATH + listsItem.posterPath,
            result.mediaImage
        )
        assertEquals(listsItem.mediaType, result.mediaType)
        assertEquals(listsItem.releaseDate, result.mediaDate)
        assertEquals(listsItem.voteAverage, result.mediaRate.toDouble())
    }
}