package com.karrar.movieapp.domain.mappers

interface Mapper<I, O> {

    fun map(input: I): O
}