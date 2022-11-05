package com.thechance.ui.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.thechance.ui.R
import com.thechance.ui.base.BaseFragment
import com.thechance.ui.databinding.FragmentMovieDetailsBinding
import com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.MediaType
import com.thechance.viewmodel.utilities.collectLast
import com.thechance.viewmodel.movieDetails.MovieDetailsUIEvent
import com.thechance.viewmodel.movieDetails.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val detailAdapter by lazy { DetailAdapter(emptyList(), viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setMovieID(args.movieId)
        setTitle(false)
        collectMovieDetailsItems()
        collectEvents()
    }

    private fun collectMovieDetailsItems() {
        binding.recyclerView.adapter = detailAdapter
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                detailAdapter.setItems(viewModel.uiState.value.detailItemResult)
            }
        }
    }

    private fun collectEvents() {
        collectLast(viewModel.movieDetailsUIEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: MovieDetailsUIEvent) {
        var action: NavDirections? = null
        when (event) {
            MovieDetailsUIEvent.ClickBackEvent -> {
                findNavController().navigateUp()
            }
            is MovieDetailsUIEvent.ClickCastEvent -> {
                action =
                    MovieDetailsFragmentDirections.actionMovieDetailFragmentToActorDetailsFragment(
                        event.castID
                    )
            }
            is MovieDetailsUIEvent.ClickMovieEvent -> {
                viewModelStore.clear()
                action = MovieDetailsFragmentDirections.actionMovieDetailsFragment(event.movieID)
            }
            MovieDetailsUIEvent.ClickPlayTrailerEvent -> {
                action =
                    MovieDetailsFragmentDirections.actionMovieDetailFragmentToYoutubePlayerActivity(
                        args.movieId, MediaType.MOVIE
                    )
            }
            MovieDetailsUIEvent.ClickReviewsEvent -> {
                action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToReviewFragment(
                    args.movieId, MediaType.TV_SHOW
                )
            }
            MovieDetailsUIEvent.ClickSaveEvent -> {
                action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToSaveMovieDialog(
                    args.movieId
                )
            }
            MovieDetailsUIEvent.MessageAppear -> {
                Toast.makeText(context, getString(R.string.submit_toast), Toast.LENGTH_SHORT).show()
            }
        }
        action?.let { findNavController().navigate(it) }

    }

}