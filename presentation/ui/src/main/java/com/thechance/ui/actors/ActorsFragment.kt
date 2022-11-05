package com.thechance.ui.actors

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.thechance.ui.R
import com.thechance.ui.adapters.LoadUIStateAdapter
import com.thechance.ui.base.BaseFragment
import com.thechance.ui.databinding.FragmentActorsBinding
import com.thechance.ui.utilities.setSpanSize
import com.thechance.viewmodel.actors.ActorsUIEvent
import com.thechance.viewmodel.actors.ActorsViewModel
import com.thechance.viewmodel.models.ActorUiState
import com.thechance.viewmodel.utilities.collect
import com.thechance.viewmodel.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorsFragment : BaseFragment<FragmentActorsBinding>() {
    override val layoutIdFragment = R.layout.fragment_actors
    override val viewModel: ActorsViewModel by viewModels()
    private val actorsAdapter by lazy { ActorsAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, resources.getString(R.string.actors))
        setAdapter()
        collectEvent()
    }

    private fun setAdapter() {
        val footerAdapter = LoadUIStateAdapter(actorsAdapter::retry)
        binding.recyclerViewActors.adapter = actorsAdapter.withLoadStateFooter(footerAdapter)

        val mManager = binding.recyclerViewActors.layoutManager as GridLayoutManager
        mManager.setSpanSize(footerAdapter, actorsAdapter, mManager.spanCount)

        collect(flow = actorsAdapter.loadStateFlow,
            action = { viewModel.setErrorUiState(it) })

        collectLast(viewModel.uiState.value.actors, ::setAllActors)
    }

    private suspend fun setAllActors(itemsPagingData: PagingData<ActorUiState>) {
        actorsAdapter.submitData(itemsPagingData)
    }

    private fun collectEvent() {
        collectLast(viewModel.actorsUIEventFlow) {
            it?.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: ActorsUIEvent) {
        when (event) {
            is ActorsUIEvent.ActorEvent -> {
                findNavController().navigate(
                    ActorsFragmentDirections.actionActorsFragmentToActorDetailsFragment(
                        event.actorID
                    )
                )
            }
            ActorsUIEvent.RetryEvent -> {
                actorsAdapter.retry()
            }
        }
    }

}