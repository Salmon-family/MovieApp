package com.devfalah.usecases.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}