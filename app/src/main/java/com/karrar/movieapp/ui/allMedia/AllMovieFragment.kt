package com.karrar.movieapp.ui.allMedia

import android.os.Bundle
import android.view.View
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
import com.karrar.movieapp.utilities.collect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

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

        collect(flow = allMediaAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setErrorUiState
        )

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

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.allMedia.collectLatest { pagingData ->
                allMediaAdapter.submitData(pagingData)
            }
        }
    }

    private fun setErrorUiState(loadState: LoadState) {
        val result = if (loadState is LoadState.Error) {
            loadState.error.localizedMessage ?: "Error"
        } else null

        if (!result.isNullOrBlank())
            viewModel._allMediaState.postValue(UIState.Error(result))
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