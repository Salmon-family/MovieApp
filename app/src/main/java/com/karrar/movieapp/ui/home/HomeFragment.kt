package com.karrar.movieapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.domain.enums.Type
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.home.adapters.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    private val homeAdapter by lazy {
        listOf(
            HorizontalAdapter<PopularMovieAdapter>(Type.PopularMovieType, viewModel),
            HorizontalAdapter<SeriesAdapter>(Type.TvShowType, viewModel),
            HorizontalAdapter<SeriesAdapter>(Type.OnTheAirType, viewModel),
            HorizontalAdapter<MovieAdapter>(Type.TrendingMovieType, viewModel),
            HorizontalAdapter<AiringTodayAdapter>(Type.AiringTodayType, viewModel),
            HorizontalAdapter<GenreAdapter>(Type.GenreType, viewModel),
            HorizontalAdapter<MovieAdapter>(Type.NowStreaming, viewModel),
            HorizontalAdapter<MovieAdapter>(Type.Upcoming, viewModel),
            HorizontalAdapter<ActorAdapter>(Type.ActorType, viewModel)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val concatAdapter = ConcatAdapter(homeAdapter)
        binding.recyclerView.adapter = concatAdapter

        viewModel.updatingRecycler.observe(viewLifecycleOwner) {
            concatAdapter.notifyDataSetChanged()
        }
    }
}