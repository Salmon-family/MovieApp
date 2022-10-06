package com.karrar.movieapp.ui.search

import androidx.lifecycle.*
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.MediaInfo
import com.karrar.movieapp.domain.models.SearchHistory
import com.karrar.movieapp.ui.base.BaseViewModel
import com.karrar.movieapp.ui.search.adapters.SearchHistoryInteractionListener
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel(), MediaInteractionListener, PersonInteractionListener, SearchHistoryInteractionListener{
    private val _media = MutableLiveData<State<List<MediaInfo>>>()
    val media: LiveData<State<List<MediaInfo>>> get() = _media

    private val _searchHistory = MutableLiveData<List<SearchHistory>>()
    val searchHistory: LiveData<List<SearchHistory>> get() = _searchHistory

    private val _clickMediaEvent = MutableLiveData<Event<Int>>()
    var clickMediaEvent: LiveData<Event<Int>> = _clickMediaEvent

    private val _clickActorEvent = MutableLiveData<Event<Int>>()
    var clickActorEvent: LiveData<Event<Int>> = _clickActorEvent

    val searchText = MutableStateFlow("")
    val mediaType = MutableStateFlow("movie")

    init {
        viewModelScope.launch {
            searchText.debounce(1000).collect{
                if (searchText.value.isNullOrEmpty()) {
                    getAllSearchHistory()
                } else {
                    when(mediaType.value){
                        "movie"  -> searchForMovie(it)
                        "tv" ->  searchForSeries(it)
                        "person" -> searchForPerson(it)
                    }
                }
            }
        }
    }

    private fun searchForPerson(text: String){
        viewModelScope.launch {
            movieRepository.searchForPerson(text).collect{
                _media.postValue(it)
            }
        }
    }

    private fun searchForMovie(text: String){
        viewModelScope.launch {
            movieRepository.searchForMovie(text).collect{
                _media.postValue(it)
            }
        }
    }

    private fun searchForSeries(text: String){
        viewModelScope.launch {
            movieRepository.searchForSeries(text).collect{
                _media.postValue(it)
            }
        }
    }

    fun onClickMovies(){
        viewModelScope.launch {
            if(mediaType.value != "movie" ){
                mediaType.emit("movie")
                searchForMovie(searchText.value)
            }
        }
    }

    fun onClickSeries(){
        viewModelScope.launch {
            if(mediaType.value != "tv" ){
                mediaType.emit("tv")
                searchForSeries(searchText.value)
            }
        }
    }

    fun onClickActors(){
        viewModelScope.launch {
            if(mediaType.value != "person" ){
                mediaType.emit("person")
                searchForPerson(searchText.value)
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
                    id = id,
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
}