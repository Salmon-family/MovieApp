package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.CreatedListDto
import com.karrar.movieapp.domain.models.CreatedList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CreatedListsMapperTest {

    private lateinit var createdListsMapper: CreatedListsMapper

    @BeforeAll
    fun setUp() {
        createdListsMapper = CreatedListsMapper()
    }

    @Test
    fun should_ReturnCreateListsMapper_when_EnterCreatedListDTO() {
        // given a CreatedListDTO object with random values
        val movieDTO = CreatedListDto(
            description = "description",
            favoriteCount = 1,
            id = 1,
            iso6391 = "iso6391",
            itemCount = 1,
            listType = "listType",
            name = "name",
            posterPath = "posterPath",
        )
        // when map is called
        val result = createdListsMapper.map(movieDTO)

        // expected
        val expectedList = CreatedList(
            id = result.id,
            itemCount = result.itemCount,
            name = result.name
        )

        // then the result should be a Create List object with the same values
        assertEquals(expectedList, result)
    }
}