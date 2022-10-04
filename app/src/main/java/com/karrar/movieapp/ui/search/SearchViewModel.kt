package com.karrar.movieapp.ui.search

import android.util.Log
import androidx.lifecycle.*
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.Person
import com.karrar.movieapp.domain.models.SearchHistory
import com.karrar.movieapp.ui.search.adapters.SearchHistoryInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel(), MediaInteractionListener, PersonInteractionListener, SearchHistoryInteractionListener{
    private val _media = MutableLiveData<State<List<Media>>>()
    val media: LiveData<State<List<Media>>> get() = _media

    private val _searchHistory = MutableLiveData<List<SearchHistory>>()
    val searchHistory: LiveData<List<SearchHistory>> get() = _searchHistory
    
    val searchText = MutableStateFlow("")
    val mediaType = MutableStateFlow("movie")

    init {
        viewModelScope.launch {
            searchText.debounce(1000).collect{
                if(!searchText.value.isNullOrEmpty()){
                    when(mediaType.value){
                        "movie"  -> searchForMovie(it)
                        "tv" ->  searchForSeries(it)
                        "person" -> searchForPerson(it)
                    }
                }else{
                    getAllSearchHistory()
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

    fun getMovies(){
        viewModelScope.launch {
            if(mediaType.value != "movie" && !searchText.value.isNullOrEmpty()){
                mediaType.emit("movie")
                searchForMovie(searchText.value)
            }
        }
    }

    fun getSeries(){
        viewModelScope.launch {
            if(mediaType.value != "tv" && !searchText.value.isNullOrEmpty()){
                mediaType.emit("tv")
                searchForSeries(searchText.value)
            }
        }
    }

    fun getActors(){
        viewModelScope.launch {
            if(mediaType.value != "person" && !searchText.value.isNullOrEmpty()){
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
    }

    override fun onClickPerson(personID: Int, name: String) {
        saveSearchResult(personID, name)
    }

    private fun saveSearchResult(id: Int, name: String){
        viewModelScope.launch {
            movieRepository.insertSearchItem(
                SearchHistoryEntity(
                    id = id,
                    Search = name
                )
            )
        }
    }

    override fun onClickSearchHistory(searchID: Int) {

    }
}