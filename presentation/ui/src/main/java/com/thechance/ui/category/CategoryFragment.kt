package com.thechance.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.thechance.ui.R
import com.thechance.ui.adapters.LoadUIStateAdapter
import com.thechance.ui.base.BaseFragment
import com.thechance.ui.databinding.FragmentCategoryBinding
import com.thechance.ui.utilities.Constants.TV_CATEGORIES_ID
import com.thechance.ui.utilities.setSpanSize
import com.thechance.viewmodel.allMedia.MediaInteractionListener
import com.thechance.viewmodel.category.CategoryViewModel
import com.thechance.viewmodel.category.uiState.CategoryUIEvent
import com.thechance.viewmodel.utilities.collect
import com.thechance.viewmodel.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override val layoutIdFragment = R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()
    private val allMediaAdapter by lazy { CategoryAdapter(viewModel as MediaInteractionListener) }
    private val args: CategoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setMedia(args.mediaId)
        setTitle(true, getTitle())
        setMediaAdapter()
        collectEvent()
        collectData()
    }

    private fun setMediaAdapter() {
        val footerAdapter = LoadUIStateAdapter(allMediaAdapter::retry)
        binding.recyclerMedia.adapter = allMediaAdapter.withLoadStateFooter(footerAdapter)

        val mManager = binding.recyclerMedia.layoutManager as GridLayoutManager
        mManager.setSpanSize(footerAdapter, allMediaAdapter, mManager.spanCount)

        collect(flow = allMediaAdapter.loadStateFlow,
            action = { viewModel.setErrorUiState(it) })
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                collectLast(viewModel.uiState.value.media)
                { allMediaAdapter.submitData(it) }
            }
        }
    }

    private fun collectEvent() {
        collect(viewModel.categoryUIEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: CategoryUIEvent) {
        when (event) {
            is CategoryUIEvent.ClickMovieEvent -> {
                if (args.mediaId == TV_CATEGORIES_ID) {
                    navigateToTvShowDetails(event.movieID)
                } else {
                    navigateToMovieDetails(event.movieID)
                }
            }
            CategoryUIEvent.RetryEvent -> {
                allMediaAdapter.retry()
            }
            is CategoryUIEvent.SelectedCategory -> {
                viewModel.getMediaList(event.categoryID)
            }
        }
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

    private fun getTitle(): String {
        return if (args.mediaId == TV_CATEGORIES_ID) {
            resources.getString(R.string.title_tv_shows)
        } else {
            resources.getString(R.string.movies)
        }
    }
}
