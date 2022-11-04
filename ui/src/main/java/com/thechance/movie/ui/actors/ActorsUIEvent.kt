package com.thechance.movie.ui.actors

sealed interface ActorsUIEvent  {
    object RetryEvent : ActorsUIEvent
    data class ActorEvent(val actorID: Int) : ActorsUIEvent
}