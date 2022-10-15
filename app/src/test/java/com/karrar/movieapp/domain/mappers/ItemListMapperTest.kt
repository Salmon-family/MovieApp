package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.ListItem
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
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

        // expected
        val expectedMedia = Media(
            mediaID = result.mediaID,
            mediaImage = result.mediaImage,
            mediaType = result.mediaType,
            mediaName = result.mediaName,
            mediaDate = result.mediaDate,
            mediaRate = result.mediaRate,
        )

        // then the result should be a genre object with the same values
        assertEquals(expectedMedia, result)
    }
}