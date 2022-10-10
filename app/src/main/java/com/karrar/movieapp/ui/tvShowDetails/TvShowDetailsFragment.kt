package com.karrar.movieapp.ui.tvShowDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentTvShowDetailsBinding
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.movieDetails.DetailAdapter
import com.karrar.movieapp.ui.movieDetails.DetailItem
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowDetailsFragment : BaseFragment<FragmentTvShowDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_tv_show_details
    override val viewModel: TvShowDetailsViewModel by viewModels()
    private lateinit var detailAdapter: DetailAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(false)

        setDetailAdapter()

        observeEvents()

        addRating()
    }

    private fun setDetailAdapter() {
        detailAdapter = DetailAdapter(mutableListOf(DetailItem.Rating(viewModel)), viewModel)
        binding.recyclerView.adapter = detailAdapter

        viewModel.tvShowDetails.observe(viewLifecycleOwner) { state ->
            state.toData()?.let { detailAdapter.addItem(DetailItem.Header(it)) }
        }
        viewModel.tvShowCast.observe(viewLifecycleOwner) { state ->
            state.toData()?.let { detailAdapter.addItem(DetailItem.Cast(it)) }
        }
        viewModel.seasons.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) detailAdapter.addItem(DetailItem.Seasons(it))
        }
        viewModel.tvShowReviews.observe(viewLifecycleOwner) {
            it.toData()?.let { items ->
                items.take(3).forEach { detailAdapter.addItem(DetailItem.Comment(it)) }
                if (items.isNotEmpty()) detailAdapter.addItem(DetailItem.ReviewText)
                if (items.count() > 3) detailAdapter.addItem(DetailItem.SeeAllReviewsButton)
            }
        }
    }

    private fun addRating() {
        viewModel.ratingValue.observe(viewLifecycleOwner) {
            it?.let { viewModel.onAddRating(it) }
        }

        viewModel.messageAppear.observe(viewLifecycleOwner, EventObserve {
            val toast =
                Toast.makeText(context, getString(R.string.submit_toast), Toast.LENGTH_SHORT)
            if (it) toast.show()
        })
    }

    private fun observeEvents() {

        viewModel.clickPlayTrailerEvent.observe(viewLifecycleOwner, EventObserve {
            viewModelStore.clear()
            val action =
                TvShowDetailsFragmentDirections.actionTvShowDetailFragmentToYoutubePlayerActivity(
                    it, MediaType.TV_SHOW
                )
            findNavController().navigate(action)
        })

        viewModel.clickCastEvent.observe(viewLifecycleOwner, EventObserve {
            val action =
                TvShowDetailsFragmentDirections.actionTvShowDetailFragmentToActorDetailsFragment(it)
            findNavController().navigate(action)
        })

        viewModel.clickReviewsEvent.observe(viewLifecycleOwner, EventObserve {
            val action =
                TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentToReviewFragment(
                    it, MediaType.TV_SHOW
                )
            findNavController().navigate(action)
        })

        viewModel.clickSaveEvent.observe(viewLifecycleOwner, EventObserve {
            val action =
                TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentToSaveMovieDialog(it)
            findNavController().navigate(action)
        })

        viewModel.clickEpisodeEvent.observe(viewLifecycleOwner, EventObserve {
            val action =
                TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentToEpisodesFragment(
                    viewModel.args.tvShowId, it
                )
            findNavController().navigate(action)
        })

        viewModel.clickBackEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigateUp()
        })

    }

}