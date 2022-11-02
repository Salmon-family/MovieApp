package com.karrar.movieapp.ui.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentMovieDetailsBinding
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var detailAdapter: DetailAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        setDetailAdapter()
        collectEvents()
    }

    private fun setDetailAdapter() {
        detailAdapter = DetailAdapter(emptyList(), viewModel)
        binding.recyclerView.adapter = detailAdapter
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
                    args.movieId, MediaType.MOVIE
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