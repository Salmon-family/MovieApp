package com.karrar.movieapp.ui.category

import androidx.lifecycle.ViewModel
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) : ViewModel(), MediaInteractionListener, CategoryInteractionListener {

    override fun onClickMedia(mediaId: Int) {}

    override fun onClickCategory(categoryId: Int) {}

}