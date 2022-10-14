package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.ListItem
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class ItemListMapper @Inject constructor() : Mapper<ListItem, Media> {
    override fun map(input: ListItem): Media {
        return Media(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            mediaType = input.mediaType ?: Constants.MOVIE,
            input.originalLanguage ?: input.originalTitle ?: "",
            input.releaseDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0F,
        )
    }
}