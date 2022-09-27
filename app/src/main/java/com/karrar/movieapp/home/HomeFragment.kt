package com.karrar.movieapp.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseFragment
import com.karrar.movieapp.data.Types
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.home.adapters.BannerAdapter
import com.karrar.movieapp.home.adapters.CategoryAdapter
import com.karrar.movieapp.home.adapters.HorizontalAdapter
import com.karrar.movieapp.home.adapters.MovieImageAdapter


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    private val homeAdapter by lazy {
        listOf(
            HorizontalAdapter<BannerAdapter>(Types.BannerType, viewModel),
            HorizontalAdapter<MovieImageAdapter>(Types.MovieType, viewModel),
            HorizontalAdapter<CategoryAdapter>(Types.CategoryType, viewModel)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val concatAdapter = ConcatAdapter(homeAdapter)
        binding.recyclerView.adapter = concatAdapter

    }

}