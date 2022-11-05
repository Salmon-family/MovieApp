package com.thechance.viewmodel.actorDetails

import androidx.lifecycle.viewModelScope
import com.thechance.ui.utilities.Event
import com.thechance.viewmodel.BaseViewModel
import com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.HomeItemsType
import com.thechance.viewmodel.movieDetails.MovieInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(
    private val getActorDetailsUseCase: com.devfalah.usecases.GetActorDetailsUseCase,
    private val getActorMoviesUseCase: com.devfalah.usecases.GetActorMoviesUseCase,
    private val actorDetailsUIMapper: ActorDetailsUIMapper,
    private val actorMoviesUIMapper: ActorMoviesUIMapper
) : BaseViewModel(), MovieInteractionListener {

    private val _actorDetailsUIState = MutableStateFlow(ActorDetailsUIState())
    val actorDetailsUIState = _actorDetailsUIState.asStateFlow()

    private val _actorDetailsUIEvent: MutableStateFlow<Event<ActorDetailsUIEvent?>> =
        MutableStateFlow(Event(null))
    val actorDetailsUIEvent = _actorDetailsUIEvent.asStateFlow()

    private var actorID = -1

    fun setActorID(actorID: Int) {
        this.actorID = actorID
        getData()
    }

    override fun getData() {
        _actorDetailsUIState.update { it.copy(isLoading = true, error = emptyList()) }
        viewModelScope.launch {
            try {
                val actorDetails = actorDetailsUIMapper.map(getActorDetailsUseCase(actorID))
                val actorMovies = getActorMoviesUseCase(actorID).map { actorMoviesUIMapper.map(it) }
                _actorDetailsUIState.update {
                    it.copy(
                        name = actorDetails.name,
                        gender = actorDetails.gender,
                        imageUrl = actorDetails.imageUrl,
                        placeOfBirth = actorDetails.placeOfBirth,
                        biography = actorDetails.biography,
                        birthday = actorDetails.birthday,
                        knownFor = actorDetails.knownFor,
                        actorMovies = actorMovies,
                        isLoading = false,
                        isSuccess = true
                    )
                }
            } catch (e: Exception) {
                onError(e.message.toString())
            }
        }
    }

    private fun onError(message: String) {
        _actorDetailsUIState.update { actorDetailsUIState ->
            actorDetailsUIState.copy(
                isLoading = false,
                error = listOf(Error(message)),
            )
        }
    }

    fun onClickBack() {
        _actorDetailsUIEvent.update { Event(ActorDetailsUIEvent.BackEvent) }
    }

    override fun onClickMovie(movieId: Int) {
        _actorDetailsUIEvent.update { Event(ActorDetailsUIEvent.ClickMovieEvent(movieId)) }
    }

    override fun onClickSeeAllMovie(homeItemsType: HomeItemsType) {
        _actorDetailsUIEvent.update { Event(ActorDetailsUIEvent.SeeAllMovies) }
    }

}