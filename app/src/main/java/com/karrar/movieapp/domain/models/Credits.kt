package com.karrar.movieapp.domain.models

import com.karrar.movieapp.data.remote.response.movieDetailsDto.CastDto


data class Credits(
    val cast: List<CastDto>?
)
