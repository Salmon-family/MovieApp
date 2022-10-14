package com.karrar.movieapp.ui.profile.watchhistory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentWatchHistoryBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchHistoryFragment : BaseFragment<FragmentWatchHistoryBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_watch_history
    override val viewModel: WatchHistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.watch_history))
        binding.recyclerViewWatchHistory.adapter = WatchHistoryAdapter(emptyList(), viewModel)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.clickMovieEvent.observeEvent(viewLifecycleOwner) { movieId ->
            findNavController().navigate(WatchHistoryFragmentDirections.actionWatchHistoryFragmentToMovieDetailFragment(
                movieId))
        }
    }
}