package com.karrar.movieapp.domain.models

data class ActorDetails(
    val id: Int,
    val name: String,
    val imageUrlActor: String,
    val birthday: String,
    val placeOfBirth: String,
    val biography: String,
    val knownForDepartment: String,
    val gender: String,
)