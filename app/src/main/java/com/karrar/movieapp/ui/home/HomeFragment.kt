package com.karrar.movieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.home.adapter.HomeAdapter
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        setAdapter()
        observeEvents()
        collectHomeData()

    }

    private fun collectHomeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.homeUiState.collect {
                homeAdapter.setItems(mutableListOf(
                    it.popularMovies,
                    it.tvShowsSeries,
                    it.onTheAiringSeries,
                    it.airingTodaySeries,
                    it.upcomingMovies,
                    it.nowStreamingMovies,
                    it.mysteryMovies,
                    it.adventureMovies,
                    it.trendingMovies,
                    it.actors,
                ))
            }
        }
    }

    private fun setAdapter() {
        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerView.adapter = homeAdapter
    }

    private fun observeEvents() {
        viewModel.clickSeeAllActorEvent.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_homeFragment_to_actorsFragment)
        }

        viewModel.clickActorEvent.observeEvent(viewLifecycleOwner) { actorID ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToActorDetailsFragment(
                actorID))
        }

        viewModel.clickMovieEvent.observeEvent(viewLifecycleOwner) { movieID ->
            navigateToMovieDetails(movieID)
        }

        viewModel.clickSeriesEvent.observeEvent(viewLifecycleOwner) { seriesID ->
            navigateToTvShowDetails(seriesID)
        }

        viewModel.clickSeeAllMovieEvent.observeEvent(viewLifecycleOwner) { typeMovieID ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllMovieFragment(
                -1,
                typeMovieID))
        }

        viewModel.clickSeeAllTVShowsEvent.observeEvent(viewLifecycleOwner) { typeTVShow ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllMovieFragment(
                -1,
                typeTVShow))
        }
    }

    private fun navigateToMovieDetails(movieID: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
            movieID))
    }

    private fun navigateToTvShowDetails(tvShowId: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTvShowDetailsFragment(
            tvShowId))
    }

}