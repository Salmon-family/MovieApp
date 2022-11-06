package com.karrar.movieapp.ui.tvShowDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentTvShowDetailsBinding
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvShowDetailsFragment : BaseFragment<FragmentTvShowDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_tv_show_details
    override val viewModel: TvShowDetailsViewModel by viewModels()
    private val args: TvShowDetailsFragmentArgs by navArgs()
    private val detailAdapter by lazy { DetailUIStateAdapter(emptyList(), viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        collectTVShowDetailsItems()
        collectEvents()
    }

    private fun collectTVShowDetailsItems() {
        binding.recyclerView.adapter = detailAdapter
        lifecycleScope.launch {
            viewModel.stateFlow.collectLatest {
                detailAdapter.setItems(viewModel.stateFlow.value.detailItemResult)
                binding.recyclerView.scrollToPosition(0)
            }
        }
    }

    private fun collectEvents() {
        collectLast(viewModel.tvShowDetailsUIEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: TvShowDetailsUIEvent) {
        var action: NavDirections? = null
        when (event) {
            TvShowDetailsUIEvent.ClickBackEvent -> {
                findNavController().navigateUp()
            }
            is TvShowDetailsUIEvent.ClickCastEvent -> {
                action =
                    TvShowDetailsFragmentDirections.actionTvShowDetailFragmentToActorDetailsFragment(
                        event.castID
                    )
            }
            is TvShowDetailsUIEvent.ClickSeasonEvent -> {
                action =
                    TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentToEpisodesFragment(args.tvShowId)
            }
            TvShowDetailsUIEvent.ClickPlayTrailerEvent -> {
                action =
                    TvShowDetailsFragmentDirections.actionTvShowDetailFragmentToYoutubePlayerActivity(
                        args.tvShowId, MediaType.TV_SHOW
                    )
            }
            TvShowDetailsUIEvent.ClickReviewsEvent -> {
                action =
                    TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentToReviewFragment(
                        args.tvShowId, MediaType.TV_SHOW
                    )
            }
            TvShowDetailsUIEvent.MessageAppear -> {
                Toast.makeText(context, getString(R.string.submit_toast), Toast.LENGTH_SHORT).show()
            }
        }
        action?.let { findNavController().navigate(it) }
    }

}
