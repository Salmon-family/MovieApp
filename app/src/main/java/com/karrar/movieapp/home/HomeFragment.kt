package com.karrar.movieapp.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseFragment
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.home.adapters.*


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val concatAdapter = ConcatAdapter(
            BannerHorizontalAdapter(BannerAdapter(emptyList(), viewModel), viewModel),
            HorizontalImageAdapter(MovieImageAdapter(emptyList(), viewModel), viewModel),
            HorizontalCategoryAdapter(CategoryAdapter(emptyList(), viewModel), viewModel)
        )

        binding.recyclerView.adapter = concatAdapter

    }

}