package com.thechance.repository.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}