package com.karrar.movieapp.ui.movieDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentMovieDetailsBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.movieReviews.ReviewAdapter
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment :BaseFragment<FragmentMovieDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()

        binding.castAdapter.adapter = CastAdapter(mutableListOf(), viewModel)
        binding.similarMovieAdapter.adapter = MovieAdapter(mutableListOf(), viewModel)
        binding.commentReviewAdapter.adapter = ReviewAdapter(mutableListOf(), viewModel)


        viewModel.getAllDetails(args.movieId)

        viewModel.ratingValue.observe(viewLifecycleOwner){
            viewModel.onAddRating(args.movieId, it)
        }

        viewModel.ratingValue.observe(viewLifecycleOwner){
           Snackbar.make(view,
               "Submitted, Thank you for your feedback",
               Toast.LENGTH_SHORT
           ).show()
        }
    }

     private fun observeEvents() {

         viewModel.clickMovieEvent.observe(viewLifecycleOwner, EventObserve{
             Navigation.findNavController(binding.root)
                 .navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf2(it))
         })

         //change the direction to cast details
         viewModel.clickCastEvent.observe(viewLifecycleOwner, EventObserve{
             Navigation.findNavController(binding.root)
                 .navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf2(it))
         })

         viewModel.clickReviewsEvent.observe(viewLifecycleOwner, EventObserve{
             Navigation.findNavController(binding.root)
                 .navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToReviewFragment(args.movieId))
         })

         viewModel.clickPlayTrailerEvent.observe(viewLifecycleOwner, EventObserve{
             Navigation.findNavController(binding.root)
                 .navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToYoutubePlayerFragment(args.movieId))
         })

         viewModel.clickSaveEvent.observe(viewLifecycleOwner, EventObserve{
             Navigation.findNavController(binding.root)
                 .navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToSaveMovieDialog(args.movieId))
         })

         viewModel.clickBackEvent.observe(viewLifecycleOwner, EventObserve{
             findNavController().navigateUp()
         })

    }

}