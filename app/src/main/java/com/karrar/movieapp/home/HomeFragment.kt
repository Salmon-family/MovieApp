package com.karrar.movieapp.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseFragment
import com.karrar.movieapp.data.Types
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.home.adapters.HorizontalAdapter


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    private val homeAdapter: List<HorizontalAdapter> by lazy {
        listOf(
            HorizontalAdapter(Types.BannerType, viewModel),
            HorizontalAdapter(Types.MovieType, viewModel),
            HorizontalAdapter(Types.CategoryType, viewModel)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val concatAdapter = ConcatAdapter(homeAdapter)
        binding.recyclerView.adapter = concatAdapter

    }

}