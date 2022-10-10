package com.karrar.movieapp.domain.mappers


class ListMapper<I, O>(private val mapper: Mapper<I, O>) : Mapper<List<I>, List<O>> {

    fun mapList(input: List<I>?): List<O> {
        return input?.let {
            this.map(it)
        } ?: emptyList()
    }

    override fun map(input: List<I>): List<O> {
        return input.mapNotNull { mapper.map(it) }
    }

}
