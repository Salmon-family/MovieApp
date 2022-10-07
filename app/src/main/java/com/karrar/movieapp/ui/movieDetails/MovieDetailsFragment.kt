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
import com.karrar.movieapp.ui.adapters.ActorAdapter
import com.karrar.movieapp.ui.adapters.HomeAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.adapters.MovieAdapter
import com.karrar.movieapp.ui.movieReviews.ReviewAdapter
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


        viewModel.movieDetails.observe(viewLifecycleOwner){
            it.toData()?.let {
                detailAdapter.addItem(DetailItem.Header(it))

            }
        }
        viewModel.movieCast.observe(viewLifecycleOwner){
            it.toData()?.let {
                detailAdapter.addItem(DetailItem.Cast(it))

            }
        }
        viewModel.similarMovie.observe(viewLifecycleOwner){
            it.toData()?.let {
                detailAdapter.addItem(DetailItem.SimilarMovies(it))

            }
        }

        viewModel.movieReviews.observe(viewLifecycleOwner){
            it.toData()?.let {
                detailAdapter.addItem(DetailItem.Comment(it,viewModel))

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
                .navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragment(it))
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
                        args.movieId
                    )
                )
        })

        viewModel.clickPlayTrailerEvent.observe(viewLifecycleOwner, EventObserve {
            Navigation.findNavController(binding.root)
                .navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailsFragmentToYoutubePlayerFragment(
                        args.movieId
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