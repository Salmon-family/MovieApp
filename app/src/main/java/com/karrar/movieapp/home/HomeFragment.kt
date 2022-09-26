package com.karrar.movieapp.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseFragment
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.home.adapters.CategoryAdapter
import com.karrar.movieapp.home.adapters.HorizontalCategoryAdapter
import com.karrar.movieapp.home.adapters.HorizontalImageAdapter
import com.karrar.movieapp.home.adapters.MovieImageAdapter


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val config = ConcatAdapter.Config.Builder().apply {
            setIsolateViewTypes(false)
        }.build()

        val concatAdapter = ConcatAdapter(
            config,
//            HorizontalCategoryAdapter(CategoryAdapter(emptyList()), viewModel),
            HorizontalImageAdapter(MovieImageAdapter(emptyList()), viewModel),
//            HorizontalCategoryAdapter(CategoryAdapter(emptyList()), viewModel)
        )

        binding.recyclerView.adapter = concatAdapter

    }

}