package com.karrar.movieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ConcatAdapter
import com.karrar.movieapp.R
import com.karrar.movieapp.data.Types
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.ui.home.adapters.*
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.movieDetails.MovieDetailsFragmentDirections
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    private val homeAdapter by lazy {
        listOf(
            HorizontalAdapter<BannerAdapter>(Types.BannerType, viewModel),
            HorizontalAdapter<MovieImageAdapter>(Types.MovieType, viewModel),
            HorizontalAdapter<CategoryAdapter>(Types.CategoryType, viewModel)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.clickItemEvent.observe(viewLifecycleOwner, EventObserve{
            Navigation.findNavController(binding.root)
                .navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(it))
        })

        val concatAdapter = ConcatAdapter(homeAdapter)
//        binding.recyclerView.adapter = concatAdapter

    }
}