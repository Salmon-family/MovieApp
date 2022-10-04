package com.karrar.movieapp.ui.category

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.domain.CategoryType
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants.FIRST_CATEGORY_ID
import com.karrar.movieapp.utilities.Constants.MOVIE_CATEGORIES_ID
import com.karrar.movieapp.utilities.Constants.TV_CATEGORIES_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) : ViewModel(), MediaInteractionListener, CategoryInteractionListener {

    val scope = viewModelScope

    val categoryTypeId = MutableLiveData<Int>()

    val movieCategories = movieRepository.getGenreList().asLiveData()
    val tvCategories = seriesRepository.getGenreList().asLiveData()

    private val _categoryType = MutableLiveData<CategoryType<List<Genre>?>>()
    val categoryType: LiveData<CategoryType<List<Genre>?>> = _categoryType

    private val _mediaList = MutableLiveData<State<List<Media>>>()
    val mediaList: LiveData<State<List<Media>>> = _mediaList


    fun setCategoryType() {
        val movieResponse = movieCategories.value?.toData()
        val tvResponse = tvCategories.value?.toData()

        when (categoryTypeId.value) {
            MOVIE_CATEGORIES_ID -> _categoryType.postValue(CategoryType.Movies(movieResponse))
            TV_CATEGORIES_ID -> _categoryType.postValue(CategoryType.TvShows(tvResponse))
        }
    }


    fun setInitialMediaList() {
        viewModelScope.launch {
            when (categoryTypeId.value) {
                MOVIE_CATEGORIES_ID -> {
                    movieRepository.getAllMovies(1).collect { _mediaList.postValue(it) }
                }
                TV_CATEGORIES_ID -> {
                    seriesRepository.getAllTvShows(1).collect { _mediaList.postValue(it) }
                }
            }
        }
    }


    override fun onClickMedia(mediaId: Int) {}


    override fun onClickCategory(categoryId: Int) {
        setMediaList(categoryId)
        if (categoryId == FIRST_CATEGORY_ID) getAllMedia()
    }

    private fun setMediaList(id: Int) {
        viewModelScope.launch {
            when (_categoryType.value) {
                CategoryType.Movies(movieCategories.value?.toData()) -> {
                    movieRepository.getMovieListByGenre(id).collect { _mediaList.postValue(it) }
                }
                CategoryType.TvShows(tvCategories.value?.toData()) -> {
                    seriesRepository.getTvListByGenre(id).collect { _mediaList.postValue(it) }
                }
                else -> {}
            }
        }
    }

    private fun getAllMedia() {
        val movieResponse = movieCategories.value?.toData()
        val tvResponse = tvCategories.value?.toData()

        viewModelScope.launch {
            when (_categoryType.value) {
                CategoryType.Movies(movieResponse) -> {
                    movieRepository.getAllMovies(1).collect { _mediaList.postValue(it) }
                }
                CategoryType.TvShows(tvResponse) -> {
                    seriesRepository.getAllTvShows(1).collect { _mediaList.postValue(it) }
                }
                else -> {}
            }
        }
    }


}