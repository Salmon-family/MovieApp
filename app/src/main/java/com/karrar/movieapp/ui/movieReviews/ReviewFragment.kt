package com.karrar.movieapp.ui.movieReviews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentReviewBinding
import com.karrar.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : BaseFragment<FragmentReviewBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_review
    override val viewModel: ReviewViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.commentReviewAdapter.adapter = ReviewAdapter(mutableListOf(), viewModel)
    }

}