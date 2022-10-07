package com.karrar.movieapp.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentCategoryBinding
import com.karrar.movieapp.ui.adapters.MediaAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override val layoutIdFragment = R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = if (viewModel.categoryTypeId == Constants.TV_CATEGORIES_ID) {
            resources.getString(R.string.title_tv_shows)
        } else {
            resources.getString(R.string.movies)
        }
        setTitle(true, title)
        
        setMediaAdapter()
        observeEvents()
        
    }

    private fun observeEvents() {
        viewModel.clickMovieEvent.observe(viewLifecycleOwner, EventObserve {
            Navigation.findNavController(binding.root)
                .navigate(
                    CategoryFragmentDirections.actionCategoryFragmentToMovieDetailFragment(it)
                )
        })
    }


        private fun setMediaAdapter() {
        binding.recyclerMedia.adapter = MediaAdapter(mutableListOf(), viewModel)
    }

}