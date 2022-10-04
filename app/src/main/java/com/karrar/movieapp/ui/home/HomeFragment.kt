package com.karrar.movieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.home.adapters.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerView.adapter = homeAdapter
        observeResponse()
    }
    private fun observeResponse() {

        viewModel.run {
            popularMovie.observe(viewLifecycleOwner) {
                it.toData()?.let { items ->
                    homeAdapter.addItem(HomeRecyclerItem.Slider(items))
                }
            }
            popularTvShow.observe(viewLifecycleOwner) {
                it.toData()?.let { items ->
                    homeAdapter.addItem(HomeRecyclerItem.TvShows(items))
                }
            }

            onTheAiring.observe(viewLifecycleOwner) {
                addMoviesWithType(it, MovieType.ON_THE_AIR, 2)
            }
            trending.observe(viewLifecycleOwner) {
                addMoviesWithType(it, MovieType.TRENDING, 3)

            }

            airingToday.observe(viewLifecycleOwner) {
                it.toData()?.let { items ->
                    homeAdapter.addItem(HomeRecyclerItem.AiringToday(items))
                }
            }
            nowStreaming.observe(viewLifecycleOwner) {
                addMoviesWithType(it, MovieType.NOW_STREAMING, 5)
            }
            upcoming.observe(viewLifecycleOwner) {
                addMoviesWithType(it, MovieType.UPCOMING, 6)

            }
            mysteryMovie.observe(viewLifecycleOwner) {
                addMoviesWithType(it, MovieType.MYSTERY, 7)


            }
            adventureMovie.observe(viewLifecycleOwner) {
                addMoviesWithType(it, MovieType.ADVENTURE, 8)

            }
            actors.observe(viewLifecycleOwner) {
                it.toData()?.let { items ->
                    homeAdapter.addItem(HomeRecyclerItem.Actor(items))
                }
            }


        }

    }

    private fun addMoviesWithType(state: State<List<Media>>, movieType: MovieType, priority: Int) {
        state.toData()?.let { items ->
            homeAdapter.addItem(HomeRecyclerItem.Movie(items, movieType)
                .apply { this.priority = priority })
        }
    }



}