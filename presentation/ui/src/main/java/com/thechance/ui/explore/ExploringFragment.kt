package com.thechance.ui.explore

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.thechance.ui.R
import com.thechance.ui.base.BaseFragment
import com.thechance.ui.databinding.FragmentExploringBinding
import com.thechance.viewmodel.explore.exploreUIState.ExploringUIEvent
import com.thechance.viewmodel.explore.exploreUIState.TrendyMediaUIState
import com.thechance.ui.utilities.Constants
import com.thechance.viewmodel.utilities.collectLast
import com.thechance.viewmodel.explore.ExploringViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExploringFragment : BaseFragment<FragmentExploringBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_exploring
    override val viewModel: ExploringViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, resources.getString(R.string.explore_label))
        collectEvent()
        binding.recyclerTrend.adapter = TrendAdapter(mutableListOf(), viewModel)
    }

    private fun collectEvent() {
        collectLast(viewModel.exploringUIEvent) {
            it?.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: ExploringUIEvent) {
        when (event) {
            ExploringUIEvent.ActorsEvent -> {
                findNavController().navigate(ExploringFragmentDirections.actionExploringFragmentToActorsFragment())
            }
            ExploringUIEvent.MoviesEvent -> {
                findNavController().navigate(
                    ExploringFragmentDirections.actionExploringFragmentToCategoryFragment(
                        Constants.MOVIE_CATEGORIES_ID
                    )
                )
            }
            ExploringUIEvent.SearchEvent -> navigateToSearch()
            ExploringUIEvent.TVShowEvent -> {
                findNavController().navigate(
                    ExploringFragmentDirections.actionExploringFragmentToCategoryFragment(
                        Constants.TV_CATEGORIES_ID
                    )
                )
            }
            is ExploringUIEvent.TrendEvent -> {
                navigateToMediaDetails(event.trendyMediaUIState)
            }
        }
    }

    private fun navigateToSearch() {
        val extras = FragmentNavigatorExtras(binding.inputSearch to "search_box")
        Navigation.findNavController(binding.root)
            .navigate(
                ExploringFragmentDirections.actionExploringFragmentToSearchFragment(),
                extras
            )
    }

    private fun navigateToMediaDetails(item: TrendyMediaUIState) {
        when (item.mediaType) {
            Constants.MOVIE -> {
                findNavController().navigate(
                    ExploringFragmentDirections.actionExploringFragmentToMovieDetailFragment(
                        item.mediaID
                    )
                )
            }
            Constants.TV_SHOWS -> {
                findNavController().navigate(
                    ExploringFragmentDirections.actionExploringFragmentToTvShowDetailsFragment(
                        item.mediaID
                    )
                )
            }
        }
    }

}