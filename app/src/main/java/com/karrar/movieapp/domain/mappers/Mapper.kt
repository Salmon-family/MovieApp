package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.actorDetailsDto.Cast

interface Mapper<I, O> {

    fun map(input: I): O
}