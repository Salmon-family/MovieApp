package com.example.usecase.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}
