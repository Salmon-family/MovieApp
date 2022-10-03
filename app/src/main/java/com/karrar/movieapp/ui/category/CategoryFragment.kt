package com.karrar.movieapp.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentCategoryBinding
import com.karrar.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override val layoutIdFragment = R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()

    private val args: CategoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categoryTypeId.value = args.mediaId

        viewModel.setInitialMediaList()

        viewModel.movieCategories.observe(viewLifecycleOwner) {
            viewModel.setCategoryType(args.mediaId)
        }
        viewModel.tvCategories.observe(viewLifecycleOwner) {
            viewModel.setCategoryType(args.mediaId)
        }

        viewModel.movieCategories.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                delay(300)
                binding.chipGroupCategory.apply {
                    this.getChildAt(0)?.id?.let { this.check(it) }
                }
            }
        }

        setMediaAdapter()
    }

    private fun setMediaAdapter() {
        binding.recyclerMedia.adapter = MediaAdapter(mutableListOf(), viewModel)
    }

}