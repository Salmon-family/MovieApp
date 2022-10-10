package com.karrar.movieapp.ui.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentMovieDetailsBinding
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
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

        detailAdapter = DetailAdapter(mutableListOf(DetailItem.Rating(viewModel)), viewModel)
        binding.recyclerView.adapter = detailAdapter


        viewModel.movieDetails.observe(viewLifecycleOwner) {
            it.toData()?.let {
                detailAdapter.addItem(DetailItem.Header(it))

            }
        }
        viewModel.movieCast.observe(viewLifecycleOwner) { item ->
            item.toData()?.let {
                detailAdapter.addItem(DetailItem.Cast(it))

            }
        }
        viewModel.similarMovie.observe(viewLifecycleOwner) {
            it.toData()?.let {
                detailAdapter.addItem(DetailItem.SimilarMovies(it))

            }
        }

        viewModel.movieReviews.observe(viewLifecycleOwner) {
            it.toData()?.let { items ->
                items.take(3).forEach { item ->
                    detailAdapter.addItem(DetailItem.Comment(item))
                }

                if (items.isNotEmpty())
                    detailAdapter.addItem(DetailItem.ReviewText)

                if (items.count() > 3)
                    detailAdapter.addItem(DetailItem.SeeAllReviewsButton)

            }
        }

        viewModel.messageAppear.observe(viewLifecycleOwner, EventObserve {
            if (it) {
                Toast.makeText(
                    context,
                    "Submitted, Thank you for your feedback",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })

        viewModel.ratingValue.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.onAddRating(args.movieId, it)
            }
        }

    }

    private fun observeEvents() {

        viewModel.clickMovieEvent.observe(viewLifecycleOwner, EventObserve {
            viewModelStore.clear()
            Navigation.findNavController(binding.root)
                .navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailFragmentToYoutubePlayerActivity(
                        it, MediaType.MOVIE
                    )
                )
        })

        viewModel.clickCastEvent.observe(viewLifecycleOwner, EventObserve {
            Navigation.findNavController(binding.root)
                .navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailFragmentToActorDetailsFragment(
                        it
                    )
                )
        })

        viewModel.clickReviewsEvent.observe(viewLifecycleOwner, EventObserve {
            Navigation.findNavController(binding.root)
                .navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailsFragmentToReviewFragment(
                        args.movieId, MediaType.MOVIE
                    )
                )
        })

        viewModel.clickPlayTrailerEvent.observe(viewLifecycleOwner, EventObserve {
            Navigation.findNavController(binding.root)
                .navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailFragmentToYoutubePlayerActivity(
                        args.movieId, MediaType.MOVIE
                    )
                )
        })

        viewModel.clickSaveEvent.observe(viewLifecycleOwner, EventObserve {
            Navigation.findNavController(binding.root)
                .navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailsFragmentToSaveMovieDialog(
                        args.movieId
                    )
                )
        })

        viewModel.clickBackEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigateUp()
        })

    }

}