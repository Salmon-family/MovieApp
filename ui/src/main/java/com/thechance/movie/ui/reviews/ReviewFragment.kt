package com.thechance.movie.ui.reviews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.thechance.movie.R
import com.thechance.movie.databinding.FragmentReviewBinding
import com.thechance.movie.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : BaseFragment<FragmentReviewBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_review
    override val viewModel: ReviewViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.review))
        binding.commentReviewAdapter.adapter = ReviewAdapter(emptyList(), viewModel)
    }

}