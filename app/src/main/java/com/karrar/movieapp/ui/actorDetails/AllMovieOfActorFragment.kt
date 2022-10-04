package com.karrar.movieapp.ui.actorDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentAllMovieOfActorBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllMovieOfActorFragment : BaseFragment<FragmentAllMovieOfActorBinding>() {
    override val layoutIdFragment = R.layout.fragment_all_movie_of_actor
    override val viewModel: ActorViewModel by viewModels()
    private val args: AllMovieOfActorFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getActorMoviesById(args.id)

        setMovieAdapter()
        observeEvents()
    }

    private fun setMovieAdapter() {
        binding.recyclerMedia.adapter = ActorMoviesAdapter(mutableListOf(), viewModel)
    }

    private fun observeEvents() {
        viewModel.backEvent.observe(viewLifecycleOwner, EventObserve { removeFragment() })
    }

    private fun removeFragment() {
        findNavController().popBackStack()
    }
}