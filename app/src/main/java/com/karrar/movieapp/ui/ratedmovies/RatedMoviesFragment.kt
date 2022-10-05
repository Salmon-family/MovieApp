package com.karrar.movieapp.ui.ratedmovies


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentRatedMoviesBinding
import com.karrar.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RatedMoviesFragment : BaseFragment<FragmentRatedMoviesBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_rated_movies
    override val viewModel: RatedMoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RatedMoviesAdapter(mutableListOf(), viewModel)
        binding.recyclerViewRatedMovies.adapter = adapter
    }
}