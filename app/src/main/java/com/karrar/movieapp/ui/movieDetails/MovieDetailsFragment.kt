package com.karrar.movieapp.ui.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentMovieDetailsBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var detailAdapter: DetailAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        setTitle(false)

        detailAdapter = DetailAdapter(emptyList(), viewModel)
        binding.recyclerView.adapter = detailAdapter


        viewModel.messageAppear.observeEvent(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    context,
                    "Submitted, Thank you for your feedback",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        viewModel.ratingValue.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.onAddRating(args.movieId, it)
            }
        }

    }

    private fun observeEvents() {
        viewModel.clickMovieEvent.observeEvent(viewLifecycleOwner) {
            viewModelStore.clear()
            findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragment(
                it))
        }
        viewModel.clickCastEvent.observeEvent(viewLifecycleOwner) {
            findNavController()
                .navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailFragmentToActorDetailsFragment(
                        it
                    )
                )
        }
        viewModel.clickReviewsEvent.observeEvent(viewLifecycleOwner) {
            findNavController()
                .navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailsFragmentToReviewFragment(
                        args.movieId
                    )
                )
        }

        viewModel.clickPlayTrailerEvent.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailFragmentToYoutubePlayerActivity(
                args.movieId)
            )
        }

        viewModel.clickSaveEvent.observeEvent(viewLifecycleOwner) {
            findNavController()
                .navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailsFragmentToSaveMovieDialog(
                        args.movieId
                    )
                )
        }

        viewModel.clickBackEvent.observeEvent(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

    }

}