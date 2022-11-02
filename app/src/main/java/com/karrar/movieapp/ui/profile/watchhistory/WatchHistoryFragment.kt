package com.karrar.movieapp.ui.profile.watchhistory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentWatchHistoryBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchHistoryFragment : BaseFragment<FragmentWatchHistoryBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_watch_history
    override val viewModel: WatchHistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.watch_history))
        binding.recyclerViewWatchHistory.adapter = WatchHistoryAdapter(emptyList(), viewModel)
        collectEvent()
    }

    private fun collectEvent() {
        collectLast(viewModel.watchHistoryUIEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: WatchHistoryUIEvent) {
        val action = when (event) {
            is WatchHistoryUIEvent.MovieEvent -> {
                WatchHistoryFragmentDirections.actionWatchHistoryFragmentToMovieDetailFragment(
                    event.movieID
                )
            }
            is WatchHistoryUIEvent.TVShowEvent -> {
                WatchHistoryFragmentDirections.actionWatchHistoryFragmentToTvShowDetailsFragment(
                    event.tvShowID
                )
            }
        }
        findNavController().navigate(action)
    }

}