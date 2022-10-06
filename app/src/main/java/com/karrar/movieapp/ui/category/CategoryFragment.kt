package com.karrar.movieapp.ui.category

import android.os.Bundle
import android.view.View
import androidx.compose.ui.unit.Constraints
import androidx.fragment.app.viewModels
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentCategoryBinding
import com.karrar.movieapp.ui.allMedia.MediaAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.Constants
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
    }

    private fun setMediaAdapter() {
        binding.recyclerMedia.adapter = MediaAdapter(mutableListOf(), viewModel)
    }

}