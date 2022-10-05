package com.karrar.movieapp.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants.FIRST_CATEGORY_ID
import com.karrar.movieapp.utilities.Constants.MOVIE_CATEGORIES_ID
import com.karrar.movieapp.utilities.Constants.TV_CATEGORIES_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) : ViewModel(), MediaInteractionListener, CategoryInteractionListener {

    val categoryTypeId = MutableLiveData<Int>()

    private val _categories = MutableLiveData<State<List<Genre>>>()
    val categories: LiveData<State<List<Genre>>> = _categories

    private val _mediaList = MutableLiveData<State<List<Media>>>()
    val mediaList: LiveData<State<List<Media>>> = _mediaList


    fun setCategoryType() {
        when (categoryTypeId.value) {
            MOVIE_CATEGORIES_ID ->
                collectResponse(movieRepository.getGenreList()) { _categories.postValue(it) }
            TV_CATEGORIES_ID ->
                collectResponse(seriesRepository.getGenreList()) { _categories.postValue(it) }
        }
    }


    override fun onClickMedia(mediaId: Int) {}


    override fun onClickCategory(categoryId: Int) {
        when (categoryId) {
            FIRST_CATEGORY_ID -> setAllMediaList()
            else -> setMediaList(categoryId)
        }
    }

    fun setAllMediaList() {
        when (categoryTypeId.value) {
            MOVIE_CATEGORIES_ID ->
                collectResponse(movieRepository.getAllMovies(1)) { _mediaList.postValue(it) }
            TV_CATEGORIES_ID ->
                collectResponse(seriesRepository.getAllTvShows(1)) { _mediaList.postValue(it) }
        }
    }

    private fun setMediaList(id: Int) {
        when (categoryTypeId.value) {
            MOVIE_CATEGORIES_ID ->
                collectResponse(movieRepository.getMovieListByGenre(id)) { _mediaList.postValue(it) }
            TV_CATEGORIES_ID ->
                collectResponse(seriesRepository.getTvShowsByGenre(id)) { _mediaList.postValue(it) }
        }
    }


    private fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO).collect { function(it) }
        }
    }

}