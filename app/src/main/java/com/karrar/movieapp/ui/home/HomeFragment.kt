package com.karrar.movieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.adapters.HomeAdapter
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(false)

        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerView.adapter = homeAdapter

        observeEvents()
        viewModel.successItems().observe(viewLifecycleOwner) { homeAdapter.addItem(it) }
    }


    override fun onStop() {
        super.onStop()
        viewModel.removeAllHomeItemsMediatorSource()
    }


    private fun observeEvents() {
        viewModel.clickSeeAllActorEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(R.id.action_homeFragment_to_actorsFragment)
        })

        viewModel.clickActorEvent.observe(viewLifecycleOwner, EventObserve { actorID ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToActorDetailsFragment(
                    actorID
                )
            )
        })

        viewModel.clickMovieEvent.observe(viewLifecycleOwner, EventObserve { movieID ->
            navigateToMovieDetails(movieID)
        })

        viewModel.clickSeriesEvent.observe(viewLifecycleOwner, EventObserve { seriesID ->
            navigateToMovieDetails(seriesID)
        })

        viewModel.clickSeeAllMovieEvent.observe(viewLifecycleOwner, EventObserve { typeMovieID ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToAllMovieFragment(
                    -1,
                    typeMovieID
                )
            )
        })
    }

    private fun navigateToMovieDetails(movieID: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                movieID
            )
        )
    }
}