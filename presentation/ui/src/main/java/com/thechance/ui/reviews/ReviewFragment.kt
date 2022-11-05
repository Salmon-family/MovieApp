package com.thechance.ui.reviews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.thechance.ui.R
import com.thechance.ui.base.BaseFragment
import com.thechance.ui.databinding.FragmentReviewBinding
import com.thechance.viewmodel.reviews.ReviewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : BaseFragment<FragmentReviewBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_review
    override val viewModel: ReviewViewModel by viewModels()
    private val args: ReviewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setData(args.type, args.mediaId)
        setTitle(true, getString(R.string.review))
        binding.commentReviewAdapter.adapter = ReviewAdapter(emptyList(), viewModel)
    }

}