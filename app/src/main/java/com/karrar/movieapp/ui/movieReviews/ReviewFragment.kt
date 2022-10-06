package com.karrar.movieapp.ui.movieReviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.databinding.FragmentReviewBinding
import com.karrar.movieapp.domain.models.Review
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.movieDetails.MovieDetailsFragmentArgs
import com.karrar.movieapp.ui.movieDetails.MovieDetailsFragmentDirections
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : BaseFragment<FragmentReviewBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_review
    override val viewModel: ReviewViewModel by viewModels()
    private val args: ReviewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.commentReviewAdapter.adapter = ReviewAdapter(mutableListOf(), viewModel)
        viewModel.getAllReviews(args.movieId)

    }


}