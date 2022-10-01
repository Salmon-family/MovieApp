package com.karrar.movieapp.utilities

fun String.checkIfGuest() =
    if (this == "") "Guest" else this
