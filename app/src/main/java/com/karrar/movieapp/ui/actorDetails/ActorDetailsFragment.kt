package com.karrar.movieapp.ui.actorDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentActorDetailsBinding
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.home.adapters.MovieAdapter
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorDetailsFragment : BaseFragment<FragmentActorDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_actor_details
    override val viewModel: ActorViewModel by viewModels()
    private val args: ActorDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(false)

        viewModel.getDetailsById(args.id)
        viewModel.getActorMoviesById(args.id)

        setMovieAdapter()
        observeEvents()
    }

    private fun setMovieAdapter() {
        binding.relatedMovieRecycler.adapter = MovieAdapter(mutableListOf(), viewModel)
    }

    private fun observeEvents() {
        viewModel.backEvent.observe(viewLifecycleOwner, EventObserve { removeFragment() })
        viewModel.seeAllMovies.observe(viewLifecycleOwner, EventObserve { seeAllMovies() })
        viewModel.clickMovieEvent.observe(
            viewLifecycleOwner,
            EventObserve { movieID -> seeMovieDetails(movieID) })
    }

    private fun seeAllMovies() {
        Navigation.findNavController(binding.root)
            .navigate(
                ActorDetailsFragmentDirections.actionActorDetailsFragmentToAllMovieOfActorFragment(
                    args.id,
                    MovieType.NON
                )
            )
    }

    private fun seeMovieDetails(movieID: Int) {
        findNavController().navigate(
            ActorDetailsFragmentDirections.actionActorDetailsFragmentToMovieDetailFragment(
                movieID
            )
        )
    }

    private fun removeFragment() {
        findNavController().popBackStack()
    }
}