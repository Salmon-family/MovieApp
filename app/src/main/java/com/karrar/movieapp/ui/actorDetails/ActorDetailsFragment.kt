package com.karrar.movieapp.ui.actorDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentActorDetailsBinding
import com.devfalah.types.AllMediaType
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorDetailsFragment : BaseFragment<FragmentActorDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_actor_details
    override val viewModel: ActorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        binding.relatedMovieRecycler.adapter = ActorMoviesAdapter(mutableListOf(), viewModel)

        collectLast(viewModel.actorDetailsUIEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: ActorDetailsUIEvent) {
        when (event) {
            ActorDetailsUIEvent.BackEvent -> {
                removeFragment()
            }
            is ActorDetailsUIEvent.ClickMovieEvent -> {
                seeMovieDetails(event.movieID)
            }
            ActorDetailsUIEvent.SeeAllMovies -> {
                navigateToActorMovies()
            }
        }
    }

    private fun navigateToActorMovies() {
        Navigation.findNavController(binding.root)
            .navigate(
                ActorDetailsFragmentDirections.actionActorDetailsFragmentToAllMovieOfActorFragment(
                    viewModel.args.id,
                    AllMediaType.ACTOR_MOVIES
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