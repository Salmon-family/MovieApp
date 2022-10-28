package com.karrar.movieapp.ui.actorDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.domain.usecases.GetActorDetailsUseCase
import com.karrar.movieapp.domain.usecases.GetActorMoviesUseCase
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(
    state: SavedStateHandle,
    private val getActorDetailsUseCase: GetActorDetailsUseCase,
    private val getActorMoviesUseCase: GetActorMoviesUseCase,
    private val actorDetailsUIMapper: ActorDetailsUIMapper,
    private val actorMoviesUIMapper: ActorMoviesUIMapper
) : BaseViewModel(), MovieInteractionListener {

    val args = ActorDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _actorDetailsUIState = MutableStateFlow(ActorDetailsUIState())
    val actorDetailsUIState = _actorDetailsUIState.asStateFlow()

    private val _backEvent = MutableLiveData<Event<Boolean>>()
    val backEvent = _backEvent.toLiveData()

    private val _seeAllMovies = MutableLiveData<Event<Boolean>>()
    val seeAllMovies = _seeAllMovies.toLiveData()

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent = _clickMovieEvent.toLiveData()

    init {
        getData()
    }

    override fun getData() {
        _actorDetailsUIState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val actorDetails = actorDetailsUIMapper.map(getActorDetailsUseCase(args.id))
                val actorMovies = getActorMoviesUseCase(args.id).map { actorMoviesUIMapper.map(it) }
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

    private fun onError(message: String){
        _actorDetailsUIState.update { actorDetailsUIState ->
            actorDetailsUIState.copy(
                isLoading = false,
                error = Error(message),
            )
        }
    }

    fun onClickBack() {
        _backEvent.postValue(Event(true))
    }

    override fun onClickMovie(movieId: Int) {
        _clickMovieEvent.postEvent(movieId)
    }

    override fun onClickSeeAllMovie(homeItemsType: HomeItemsType) {
        _seeAllMovies.postValue(Event(true))
    }


}