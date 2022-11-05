package com.thechance.ui.tvShowDetails.episodes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.thechance.ui.R
import com.thechance.ui.base.BaseFragment
import com.thechance.ui.databinding.FragmentEpisodesBinding
import com.thechance.viewmodel.tvShowDetails.episodes.EpisodesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding>() {

    override val layoutIdFragment = R.layout.fragment_episodes
    override val viewModel: EpisodesViewModel by viewModels()
    private val args: EpisodesFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setTVShowData(args.tvShowId)
        setTitle(true, getString(R.string.episodes))
        setEpisodesAdapter()
    }

    private fun setEpisodesAdapter() {
        binding.recyclerView.adapter = EpisodeAdapter(mutableListOf(), viewModel)
    }
}
