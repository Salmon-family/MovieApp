package com.karrar.movieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.karrar.movieapp.R
import com.karrar.movieapp.data.Types
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.home.adapters.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    private val homeAdapter by lazy {
        listOf(
            HorizontalAdapter<PopularMovieAdapter>(Types.PopularMovieType, viewModel),
            HorizontalAdapter<MovieImageAdapter>(Types.MovieType, viewModel),
            HorizontalAdapter<AiringTodayAdapter>(Types.AiringTodayType, viewModel),
            HorizontalAdapter<CategoryAdapter>(Types.CategoryType, viewModel),
            HorizontalAdapter<ActorAdapter>(Types.ActorType, viewModel)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val concatAdapter = ConcatAdapter(homeAdapter)
        binding.recyclerView.adapter = concatAdapter

    }
}