package com.karrar.movieapp.ui.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.*
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentMovieDetailsBinding
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.*
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
        showMessageOfChangeRating()
        observeEvents()
    }

    private fun setDetailAdapter() {
        detailAdapter = DetailAdapter(emptyList(), viewModel)
        binding.recyclerView.adapter = detailAdapter
    }

    private fun showMessageOfChangeRating() {
        viewModel.messageAppear.observe(viewLifecycleOwner) {
            if (viewModel.messageAppear.value == true) {
                Toast.makeText(
                    context,
                    getString(R.string.submit_toast),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


    private fun observeEvents() {
        viewModel.clickMovieEvent.observeEvent(viewLifecycleOwner) {
            viewModelStore.clear()
            navigateToMovie(it)
        }

        viewModel.clickPlayTrailerEvent.observeEvent(viewLifecycleOwner) { navigateToTrailer() }

        viewModel.clickCastEvent.observeEvent(viewLifecycleOwner) { navigateToCast(it) }

        viewModel.clickReviewsEvent.observeEvent(viewLifecycleOwner) { navigateToReviews() }

        viewModel.clickSaveEvent.observeEvent(viewLifecycleOwner) { navigateToSave() }

        viewModel.clickBackEvent.observeEvent(viewLifecycleOwner) { goBack() }
    }

    private fun navigateToTrailer() {
        val action =
            MovieDetailsFragmentDirections.actionMovieDetailFragmentToYoutubePlayerActivity(
                args.movieId, MediaType.MOVIE
            )
        findNavController().navigate(action)
    }

    private fun navigateToMovie(movieId: Int) {
        val action = MovieDetailsFragmentDirections.actionMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }

    private fun navigateToCast(castId: Int) {
        val action =
            MovieDetailsFragmentDirections.actionMovieDetailFragmentToActorDetailsFragment(castId)
        findNavController().navigate(action)
    }

    private fun navigateToReviews() {
        val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToReviewFragment(
            args.movieId, MediaType.MOVIE
        )
        findNavController().navigate(action)
    }

    private fun navigateToSave() {
        val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToSaveMovieDialog(
            args.movieId
        )
        findNavController().navigate(action)
    }

    private fun goBack() {
        findNavController().navigateUp()
    }

}