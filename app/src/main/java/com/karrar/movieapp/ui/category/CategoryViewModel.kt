package com.karrar.movieapp.ui.category

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.utilities.Constants.FIRST_CATEGORY_ID
import com.karrar.movieapp.utilities.Constants.MOVIE_CATEGORIES_ID
import com.karrar.movieapp.utilities.Constants.TV_CATEGORIES_ID
import com.karrar.movieapp.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    state: SavedStateHandle
) : ViewModel(), MediaInteractionListener, CategoryInteractionListener {

    private val args = CategoryFragmentArgs.fromSavedStateHandle(state)

    val categoryTypeId = args.mediaId

    private val _categories = MutableLiveData<State<List<Genre>>?>()
    val categories: LiveData<State<List<Genre>>?> = _categories

    private val _mediaList = MutableLiveData<State<List<Media>>?>()
    val mediaList: LiveData<State<List<Media>>?> = _mediaList

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    var clickMovieEvent: LiveData<Event<Int>> = _clickMovieEvent

    init {
        setCategoryType()
        setAllMediaList()
    }


    private fun setCategoryType() {
        when (categoryTypeId) {
            MOVIE_CATEGORIES_ID ->
                collectResponse(movieRepository.getMovieGenreList()) { _categories.postValue(it) }
            TV_CATEGORIES_ID ->
                collectResponse(seriesRepository.getTVShowsGenreList()) { _categories.postValue(it) }
        }
    }


    override fun onClickMedia(mediaId: Int) {
        _clickMovieEvent.postValue(Event(mediaId))
    }


    override fun onClickCategory(categoryId: Int) {
        when (categoryId) {
            FIRST_CATEGORY_ID -> setAllMediaList()
            else -> setMediaList(categoryId)
        }
    }

    private fun setAllMediaList() {
        when (categoryTypeId) {
            MOVIE_CATEGORIES_ID ->
                collectResponse(movieRepository.getAllMovies()) { _mediaList.postValue(it) }
            TV_CATEGORIES_ID ->
                collectResponse(seriesRepository.getAllTvShows()) { _mediaList.postValue(it) }
        }
    }

    private fun setMediaList(id: Int) {
        when (categoryTypeId) {
            MOVIE_CATEGORIES_ID ->
                collectResponse(movieRepository.getMovieListByGenreID(id)) { _mediaList.postValue(it) }
            TV_CATEGORIES_ID ->
                collectResponse(seriesRepository.getTvShowsByGenreID(id)) { _mediaList.postValue(it) }
        }
    }


    private fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO).collect { function(it) }
        }
    }

}