package com.karrar.movieapp.ui.tvShowDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentTvShowDetailsBinding
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowDetailsFragment : BaseFragment<FragmentTvShowDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_tv_show_details
    override val viewModel: TvShowDetailsViewModel by viewModels()
    private val args: TvShowDetailsFragmentArgs by navArgs()
    private lateinit var detailAdapter: DetailUIStateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(false)

        setDetailAdapter()
        addRating()
        observeEvents()
    }


    private fun setDetailAdapter() {
        detailAdapter = DetailUIStateAdapter(emptyList(), viewModel)
        binding.recyclerView.adapter = detailAdapter
    }


    private fun addRating() {
        val toast = Toast.makeText(
            context,
            getString(R.string.submit_toast),
            Toast.LENGTH_SHORT
        )
        if (viewModel.stateFlow.value.messageAppear) toast.show()
    }


    private fun observeEvents() {
        viewModel.clickPlayTrailerEvent.observeEvent(viewLifecycleOwner) { navigateToTrailer() }

        viewModel.clickCastEvent.observeEvent(viewLifecycleOwner) { navigateToCast(it) }

        viewModel.clickReviewsEvent.observeEvent(viewLifecycleOwner) { navigateToReviews() }

        viewModel.clickEpisodeEvent.observeEvent(viewLifecycleOwner) { navigateToEpisodes(it) }

        viewModel.clickBackEvent.observeEvent(viewLifecycleOwner) { goBack() }
    }

    private fun navigateToTrailer() {
        val action =
            TvShowDetailsFragmentDirections.actionTvShowDetailFragmentToYoutubePlayerActivity(
                args.tvShowId, MediaType.TV_SHOW
            )
        findNavController().navigate(action)
    }

    private fun navigateToCast(castId: Int) {
        val action =
            TvShowDetailsFragmentDirections.actionTvShowDetailFragmentToActorDetailsFragment(castId)
        findNavController().navigate(action)
    }

    private fun navigateToReviews() {
        val action =
            TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentToReviewFragment(
                args.tvShowId, MediaType.TV_SHOW
            )
        findNavController().navigate(action)
    }

    private fun navigateToEpisodes(seasonNumber: Int) {
        val action =
            TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentToEpisodesFragment(
                args.tvShowId, seasonNumber
            )
        findNavController().navigate(action)
    }

    private fun goBack() {
        findNavController().navigateUp()
    }

}