package com.karrar.movieapp.ui.actors

sealed class ActorsUIEvent  {
    object RetryEvent : ActorsUIEvent()
    data class ActorEvent(val actorID: Int) : ActorsUIEvent()
}