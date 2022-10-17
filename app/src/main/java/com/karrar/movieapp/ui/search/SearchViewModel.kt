package com.karrar.movieapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.SearchHistory
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.search.adapters.MediaSearchInteractionListener
import com.karrar.movieapp.ui.search.adapters.ActorSearchInteractionListener
import com.karrar.movieapp.ui.search.adapters.SearchHistoryInteractionListener
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import com.karrar.movieapp.utilities.postEvent
import com.karrar.movieapp.utilities.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel(), MediaSearchInteractionListener, ActorSearchInteractionListener,
    SearchHistoryInteractionListener {

    lateinit var media: Flow<PagingData<Media>>

    private val _mediaState = MutableLiveData<UIState<Boolean>>(UIState.Loading)
    val mediaState = _mediaState.toLiveData()

    private val _searchHistory = MutableLiveData<List<SearchHistory>>()
    val searchHistory = _searchHistory.toLiveData()

    private val _clickMediaEvent = MutableLiveData<Event<Media>>()
    var clickMediaEvent = _clickMediaEvent.toLiveData()

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    var clickActorEvent = _clickActorEvent.toLiveData()

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent = _clickBackEvent.toLiveData()

    private val _clickRetryEvent = MutableLiveData<Event<Boolean>>()
    val clickRetryEvent = _clickRetryEvent.toLiveData()

    val searchText = MutableStateFlow("")
    val mediaType = MutableStateFlow(Constants.MOVIE)

    init {
        viewModelScope.launch {
            media = movieRepository.searchForMovie(searchText.value).flow.cachedIn(viewModelScope)
            getAllSearchHistory()
            searchText.debounce(1000).collect {
                if (searchText.value.isNotBlank()) {
                    when (mediaType.value) {
                        Constants.MOVIE -> searchForMovie(it)
                        Constants.TV_SHOWS -> searchForSeries(it)
                        Constants.ACTOR -> searchForActor(it)
                    }
                }
            }
        }
    }

    override fun getData() {
        _clickRetryEvent.postEvent(true)
    }

    private fun searchForActor(text: String) {
        viewModelScope.launch {
            media = movieRepository.searchForActor(text).flow.cachedIn(viewModelScope)
        }
    }

    private fun searchForMovie(text: String) {
        viewModelScope.launch {
            media = movieRepository.searchForMovie(text).flow.cachedIn(viewModelScope)
        }
    }

    private fun searchForSeries(text: String) {
        viewModelScope.launch {
            media = movieRepository.searchForSeries(text).flow.cachedIn(viewModelScope)
        }
    }

    fun onClickMovies() {
        viewModelScope.launch {
            if (mediaType.value != Constants.MOVIE) {
                mediaType.emit(Constants.MOVIE)
                searchForMovie(searchText.value)
            }
        }
    }

    fun onClickSeries() {
        viewModelScope.launch {
            if (mediaType.value != Constants.TV_SHOWS) {
                mediaType.emit(Constants.TV_SHOWS)
                searchForSeries(searchText.value)
            }
        }
    }

    fun onClickActors() {
        viewModelScope.launch {
            if (mediaType.value != Constants.ACTOR) {
                mediaType.emit(Constants.PERSON)
                searchForActor(searchText.value)
            }
        }
    }

    private fun getAllSearchHistory() {
        viewModelScope.launch {
            movieRepository.getAllSearchHistory().collect {
                _searchHistory.postValue(it)
            }
        }
    }

    override fun onClickMedia(media: Media) {
        saveSearchResult(media.mediaID, media.mediaName)
        _clickMediaEvent.postEvent(media)
    }

    override fun onClickPerson(personID: Int, name: String) {
        saveSearchResult(personID, name)
        _clickActorEvent.postEvent(personID)
    }

    private fun saveSearchResult(id: Int, name: String) {
        viewModelScope.launch {
            movieRepository.insertSearchItem(
                SearchHistoryEntity(
                    id = id.toLong(),
                    search = name
                )
            )
        }
    }

    override fun onClickSearchHistory(name: String) {
        viewModelScope.launch {
            searchText.emit(name)
        }
    }

    fun onClickBack() {
        _clickBackEvent.postEvent(true)
    }

    fun setErrorUiState(loadState: LoadState) {
        val result = if (loadState is LoadState.Error) {
            loadState.error.message ?: "Error"
        } else null

        if (!result.isNullOrBlank()) {
            _mediaState.postValue(UIState.Error(result))
        } else {
            _mediaState.postValue(UIState.Success(true))
        }
    }
}