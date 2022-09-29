package com.karrar.movieapp.ui.movieDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentMovieDetailsBinding
import com.karrar.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment :BaseFragment<FragmentMovieDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.castAdapter.adapter = CastAdapter(mutableListOf(), viewModel)
        binding.similarMovieAdapter.adapter = MovieAdapter(mutableListOf(), viewModel)

        viewModel.similarMovie.observe(viewLifecycleOwner) {
            Log.i("kkk", it.toString())
        }

    }

}