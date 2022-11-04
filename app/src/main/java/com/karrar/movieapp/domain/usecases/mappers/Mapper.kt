package com.karrar.movieapp.domain.usecases.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}