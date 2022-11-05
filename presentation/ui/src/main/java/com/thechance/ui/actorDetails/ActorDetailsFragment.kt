package com.thechance.ui.actorDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.thechance.ui.R
import com.thechance.ui.base.BaseFragment
import com.thechance.ui.databinding.FragmentActorDetailsBinding
import com.thechance.viewmodel.actorDetails.ActorDetailsUIEvent
import com.thechance.viewmodel.actorDetails.ActorViewModel
import com.thechance.viewmodel.category.com.thechance.viewmodel.utilities.AllMediaType
import com.thechance.viewmodel.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorDetailsFragment : BaseFragment<FragmentActorDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_actor_details
    override val viewModel: ActorViewModel by viewModels()
    private val args: ActorDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setActorID(args.id)
        setTitle(false)
        binding.relatedMovieRecycler.adapter =
            ActorMoviesAdapter(
                mutableListOf(),
                viewModel
            )

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
                    args.id,
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