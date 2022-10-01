package com.karrar.movieapp.ui.movieDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout
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

        viewModel.getAllDetails(985939)

        binding.castAdapter.adapter = CastAdapter(mutableListOf(), viewModel)
        binding.similarMovieAdapter.adapter = MovieAdapter(mutableListOf(), viewModel)
        binding.commentReviewAdapter.adapter = ReviewAdapter(mutableListOf(), viewModel)

        viewModel.rating.observe(viewLifecycleOwner){
           Log.i("kkk", it.toString())
        }

        viewModel.ratingValue.observe(viewLifecycleOwner){
           Snackbar.make(view,
               "Submitted, Thank you for your feedback",
               Toast.LENGTH_SHORT
           ).show()
        }

    }

}