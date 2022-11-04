package com.devfalah.usecases.home.mappers

import com.devfalah.usecases.mappers.Mapper


class ListMapper<I, O>(private val mapper: Mapper<I, O>) : Mapper<List<I>, List<O>> {

    fun mapList(input: List<I?>?): List<O> {
        return input?.let {
            this.mapNullItem(it)
        } ?: emptyList()
    }

    private fun mapNullItem(input: List<I?>): List<O> {
        return input.mapNotNull {
            it?.let {
                mapper.map(it)
            }
        }
    }

    override fun map(input: List<I>): List<O> {
        return input.mapNotNull { mapper.map(it) }
    }

}
