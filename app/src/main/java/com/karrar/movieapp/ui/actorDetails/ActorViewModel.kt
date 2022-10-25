package com.karrar.movieapp.ui.actorDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.domain.models.ActorDetails
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
    private val state: SavedStateHandle,
    private val getActorDetailsUseCase: GetActorDetailsUseCase,
    private val getActorMoviesUseCase: GetActorMoviesUseCase,
) : BaseViewModel(), MovieInteractionListener {

    val args = ActorDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _actorDetails = MutableStateFlow(ActorDetailsUIState(isLoading = true))
    val actorDetails = _actorDetails.asStateFlow()

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
        viewModelScope.launch {
            try {
                val actorDetails = getActorDetailsUseCase(args.id)
                onShowActorDetails(actorDetails)
            } catch (e: Exception) {
                onError(e.message.toString())
            }
        }
    }

    private fun onError(message: String){
        _actorDetails.update { actorDetailsUIState ->
            actorDetailsUIState.copy(
                isLoading = false,
                error = Error(message),
            )
        }
    }

    private suspend fun onShowActorDetails(actorDetails: ActorDetails){
        _actorDetails.update { actorDetailsUIState ->
            actorDetailsUIState.copy(
                name = actorDetails.actorName,
                imageUrl = actorDetails.actorImage,
                gender = actorDetails.actorGender,
                birthday = actorDetails.actorBirthday,
                placeOfBirth = actorDetails.actorPlaceOfBirth,
                knownFor = actorDetails.knownForDepartment,
                biography = actorDetails.actorBiography,
                actorMovies = getActorMoviesUseCase(args.id),
                isLoading = false,
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