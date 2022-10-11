package com.karrar.movieapp.ui.category

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.data.repository.SeriesRepository
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BaseViewModel
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
) : BaseViewModel(), MediaInteractionListener, CategoryInteractionListener {

    private val args = CategoryFragmentArgs.fromSavedStateHandle(state)

    val categoryTypeId = args.mediaId

    private val _categories = MutableLiveData<UIState<List<Genre>>?>()
    val categories: LiveData<UIState<List<Genre>>?> = _categories

    private val _mediaList = MutableLiveData<UIState<List<Media>>?>()
    val mediaList: LiveData<UIState<List<Media>>?> = _mediaList

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    var clickMovieEvent: LiveData<Event<Int>> = _clickMovieEvent

    init {
        setCategoryType()
        setAllMediaList()
    }


    private fun setCategoryType() {
        _categories.postValue(UIState.Loading)
        when (categoryTypeId) {
            MOVIE_CATEGORIES_ID -> wrapWithState({
                val response = movieRepository.getMovieGenreList2()
                _categories.postValue(UIState.Success(response))
            }, {})
            TV_CATEGORIES_ID -> wrapWithState({
                val response = seriesRepository.getTVShowsGenreList()
                _categories.postValue(UIState.Success(response))
            }, {})
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
            MOVIE_CATEGORIES_ID -> wrapWithState({
                val response = movieRepository.getAllMovies()
                _mediaList.postValue(UIState.Success(response))
            }, {})
            TV_CATEGORIES_ID -> wrapWithState({
                val response = seriesRepository.getAllTvShows()
                _mediaList.postValue(UIState.Success(response))
            }, {})

        }
    }

    private fun setMediaList(id: Int) {
        _categories.postValue(UIState.Loading)
        when (categoryTypeId) {
            MOVIE_CATEGORIES_ID -> wrapWithState({
                val response = movieRepository.getMovieListByGenreID2(id)
                _mediaList.postValue(UIState.Success(response))
            }, {})
            TV_CATEGORIES_ID -> wrapWithState({
                val response = seriesRepository.getTvShowsByGenreID(id)
                _mediaList.postValue(UIState.Success(response))
            }, {})

        }
    }
}


//    private fun <T> collectResponse(flow: Flow<State<T>>, function: (State<T>) -> Unit) {
//        viewModelScope.launch {
//            flow.flowOn(Dispatchers.IO).collect { function(it) }
//        }
//    }
