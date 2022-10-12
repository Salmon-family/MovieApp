package com.karrar.movieapp.ui.allMedia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentAllMovieBinding
import com.karrar.movieapp.ui.UIState
import com.karrar.movieapp.ui.adapters.MediaLoadStateAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AllMovieFragment : BaseFragment<FragmentAllMovieBinding>() {
    override val layoutIdFragment = R.layout.fragment_all_movie
    override val viewModel: AllMovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, viewModel.args.type.value)
        setMovieAdapter()
        observeEvents()
    }

    private fun setMovieAdapter() {
        val allMediaAdapter = AllMediaAdapter(viewModel)
        val footerAdapter = MediaLoadStateAdapter(allMediaAdapter::retry)

        binding.recyclerMedia.adapter =
            allMediaAdapter.withLoadStateFooter(footerAdapter)

        val mManager = binding.recyclerMedia.layoutManager as GridLayoutManager
        mManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if ((position == allMediaAdapter.itemCount)
                    && footerAdapter.itemCount > 0
                ) {
                    mManager.spanCount
                } else {
                    1
                }
            }
        }

        allMediaAdapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                viewModel.allMediaState.postValue(UIState.Loading)
            else {
                viewModel.allMediaState.postValue(UIState.Success(true))
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    viewModel.allMediaState.postValue(UIState.Error(it.error.toString()))
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.allMedia.collectLatest { pagingData ->
                allMediaAdapter.submitData(pagingData)
            }

        }
    }

    private fun observeEvents() {
        viewModel.backEvent.observe(viewLifecycleOwner, EventObserve { removeFragment() })
        viewModel.clickMovieEvent.observe(
            viewLifecycleOwner,
            EventObserve { movieID -> seeMovieDetails(movieID) })
    }

    private fun seeMovieDetails(movieID: Int) {
        findNavController().navigate(
            AllMovieFragmentDirections.actionAllMovieFragmentToMovieDetailFragment(
                movieID
            )
        )
    }

    private fun removeFragment() {
        findNavController().popBackStack()
    }

}