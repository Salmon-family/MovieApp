package com.karrar.movieapp.ui.actors

sealed interface ActorsUIEvent  {
    object RetryEvent : ActorsUIEvent
    data class ActorEvent(val actorID: Int) : ActorsUIEvent
}