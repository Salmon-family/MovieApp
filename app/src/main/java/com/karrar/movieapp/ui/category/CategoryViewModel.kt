package com.karrar.movieapp.ui.category

import androidx.lifecycle.*
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.domain.CategoryType
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants.ACTION_AND_ADVENTURE_ID
import com.karrar.movieapp.utilities.Constants.ACTION_ID
import com.karrar.movieapp.utilities.Constants.MOVIE_CATEGORIES_ID
import com.karrar.movieapp.utilities.Constants.TV_CATEGORIES_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) : ViewModel(), MediaInteractionListener, CategoryInteractionListener {

    private val _categoryType = MutableLiveData<CategoryType<List<Genre>?>>()
    val categoryTypeCategoryType: LiveData<CategoryType<List<Genre>?>> = _categoryType

    private val _mediaList = MutableLiveData<State<List<Media>>>()
    val mediaList: LiveData<State<List<Media>>> = _mediaList

    val movieCategories = movieRepository.getGenreList().asLiveData()
    val tvCategories = seriesRepository.getGenreList().asLiveData()

    val categoryTypeId = MutableLiveData<Int>()
    private val mediaListId = MutableLiveData<Int>()


    fun setCategoryType(id: Int) {
        val movieResponse = movieCategories.value?.toData()
        val tvResponse = tvCategories.value?.toData()

        when (id) {
            MOVIE_CATEGORIES_ID -> _categoryType.postValue(CategoryType.MovieFoo(movieResponse))
            TV_CATEGORIES_ID -> _categoryType.postValue(CategoryType.TvFoo(tvResponse))
        }
    }


    fun setInitialMediaListId() {
        viewModelScope.launch {
            when (categoryTypeId.value) {
                MOVIE_CATEGORIES_ID -> {
                    movieRepository.getMovieListByGenre(ACTION_ID)
                        .collect { _mediaList.postValue(it) }
                }
                TV_CATEGORIES_ID -> seriesRepository.getTvListByGenre(ACTION_AND_ADVENTURE_ID)
                    .collect { _mediaList.postValue(it) }
            }
        }
    }


    override fun onClickMedia(mediaId: Int) {}

    override fun onClickCategory(categoryId: Int) {
        mediaListId.postValue(categoryId)
        setMediaList()
    }

    private fun setMediaList() {
        viewModelScope.launch {
            delay(300)
            mediaListId.value?.let {
                when (_categoryType.value) {
                    CategoryType.MovieFoo(movieCategories.value?.toData()) -> {
                        movieRepository.getMovieListByGenre(it).collect { _mediaList.postValue(it) }
                    }
                    CategoryType.TvFoo(tvCategories.value?.toData()) -> {
                        seriesRepository.getTvListByGenre(it).collect { _mediaList.postValue(it) }
                    }
                    else -> {}
                }
            }
        }
    }


}