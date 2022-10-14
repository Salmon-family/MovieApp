package com.karrar.movieapp.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentCategoryBinding
import com.karrar.movieapp.ui.adapters.MediaAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.Constants.TV_CATEGORIES_ID
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override val layoutIdFragment = R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getTitle())
        setMediaAdapter()
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.clickMovieEvent.observe(viewLifecycleOwner, EventObserve {
            if (viewModel.args.mediaId == TV_CATEGORIES_ID) {
                navigateToTvShowDetails(it)
            } else {
                navigateToMovieDetails(it)
            }
        })
    }

    private fun navigateToMovieDetails(movieId: Int) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToMovieDetailFragment(movieId)
        findNavController().navigate(action)
    }

    private fun navigateToTvShowDetails(tvShowId: Int) {
        val action =
            CategoryFragmentDirections.actionCategoryFragmentToTvShowDetailsFragment(tvShowId)
        findNavController().navigate(action)
    }


    private fun setMediaAdapter() {
        binding.recyclerMedia.adapter =
            MediaAdapter(mutableListOf(), R.layout.item_media, viewModel)
    }

    private fun getTitle(): String {
        return if (viewModel.args.mediaId == TV_CATEGORIES_ID) {
            resources.getString(R.string.title_tv_shows)
        } else {
            resources.getString(R.string.movies)
        }
    }
}