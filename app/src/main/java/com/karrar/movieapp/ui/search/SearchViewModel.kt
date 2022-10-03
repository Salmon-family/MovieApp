package com.karrar.movieapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel(), MediaInteractionListener, PersonInteractionListener{
    private val _media = MutableLiveData<State<List<Media>>>()
    val media: LiveData<State<List<Media>>> get() = _media

    private val _person = MutableLiveData<State<List<Person>>>()
    val person: LiveData<State<List<Person>>> get() = _person

    val searchText = MutableStateFlow("")
    val mediaType = MutableStateFlow("movie")

    init {
        viewModelScope.launch {
            searchText.debounce(1000).collect{
                searchForMedia(mediaType.value,it)
                searchForPerson(it)
            }
        }
    }

    private fun searchForMedia(type: String, text: String){
        viewModelScope.launch {
            movieRepository.searchForMedia(type, text).collect{
                _media.postValue(it)
            }
        }
    }

    private fun searchForPerson(text: String){
        viewModelScope.launch {
            movieRepository.searchForPerson(text).collect{
                _person.postValue(it)
            }
        }
    }

    fun getMovies(){
        viewModelScope.launch {
            mediaType.emit("movie")
            searchForMedia(mediaType.value,searchText.value)
        }
    }

    fun getSeries(){
        viewModelScope.launch {
            mediaType.emit("tv")
            searchForMedia(mediaType.value,searchText.value)
        }
    }

    fun getActors(){
        viewModelScope.launch {
            mediaType.emit("person")
            searchForPerson(searchText.value)
        }
    }

    override fun onClickMedia(mediaID: Int) {

    }

    override fun onClickPerson(personID: Int) {

    }
}