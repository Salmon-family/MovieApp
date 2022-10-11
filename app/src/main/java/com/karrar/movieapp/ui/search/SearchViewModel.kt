package com.karrar.movieapp.ui.search

import androidx.lifecycle.*
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.SearchHistory
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.search.adapters.PersonInteractionListener
import com.karrar.movieapp.ui.search.adapters.SearchHistoryInteractionListener
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel(), MediaSearchInteractionListener, PersonInteractionListener, SearchHistoryInteractionListener{
    private val _media = MutableLiveData<UIState<List<Media>>>()
    val media: LiveData<UIState<List<Media>>> get() = _media

    private val _searchHistory = MutableLiveData<List<SearchHistory>>()
    val searchHistory: LiveData<List<SearchHistory>> get() = _searchHistory

    private val _clickMediaEvent = MutableLiveData<Event<Int>>()
    var clickMediaEvent: LiveData<Event<Int>> = _clickMediaEvent

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    var clickActorEvent: LiveData<Event<Int>> = _clickActorEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    val searchText = MutableStateFlow("")
    val mediaType = MutableStateFlow("movie")

    init {
        viewModelScope.launch {
            searchText.debounce(1000).collect{
                if (searchText.value.isNullOrEmpty()) {
                    getAllSearchHistory()
                } else {
                    when(mediaType.value){
                        Constants.MOVIE  -> searchForMovie(it)
                        Constants.TV_SHOWS ->  searchForSeries(it)
                        Constants.PERSON -> searchForActor(it)
                    }
                }
            }
        }
    }

    private fun searchForActor(text: String){
        wrapWithUIState({movieRepository.searchForActor(text)}, _media)
    }

    private fun searchForMovie(text: String){
        wrapWithUIState({movieRepository.searchForMovie(text)}, _media)
    }

    private fun searchForSeries(text: String){
        wrapWithUIState({movieRepository.searchForSeries(text)}, _media)
    }

    fun onClickMovies(){
        viewModelScope.launch {
            if(mediaType.value != Constants.MOVIE ){
                mediaType.emit(Constants.MOVIE)
                searchForMovie(searchText.value)
            }
        }
    }

    fun onClickSeries(){
        viewModelScope.launch {
            if(mediaType.value != Constants.TV_SHOWS ){
                mediaType.emit(Constants.TV_SHOWS)
                searchForSeries(searchText.value)
            }
        }
    }

    fun onClickActors(){
        viewModelScope.launch {
            if(mediaType.value != Constants.PERSON ){
                mediaType.emit(Constants.PERSON)
                searchForActor(searchText.value)
            }
        }
    }

    fun getAllSearchHistory(){
        viewModelScope.launch {
            movieRepository.getAllSearchHistory().collect{
                _searchHistory.postValue(it)
            }
        }
    }

    override fun onClickMedia(mediaID: Int, name: String) {
        saveSearchResult(mediaID, name)
        _clickMediaEvent.postValue(Event(mediaID))
    }

    override fun onClickPerson(personID: Int, name: String) {
        saveSearchResult(personID, name)
        _clickActorEvent.postValue(Event(personID))
    }

    private fun saveSearchResult(id: Int, name: String){
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

    fun onClickBack(){
        _clickBackEvent.postValue(Event(true))
    }
}