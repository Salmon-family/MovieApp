package com.karrar.movieapp.ui.saved

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentAllSavedListsBinding
import com.karrar.movieapp.ui.adapters.HomeAdapter
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllSavedListsFragment : BaseFragment<FragmentAllSavedListsBinding>() {
    override val layoutIdFragment = R.layout.fragment_all_saved_lists
    override val viewModel: AllSavedListsViewModel by viewModels()
    private lateinit var savedListsAdapter: SavedListsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSavedListsAdapter()
        observeEvents()
    }


    private fun setSavedListsAdapter() {
        savedListsAdapter = SavedListsAdapter(mutableListOf(), viewModel)
        binding.lists.adapter = savedListsAdapter
    }

    private fun observeEvents() {

        viewModel.isButtonClicked.observe(viewLifecycleOwner, EventObserve {
            if (it) {
                navigateToCreateListDialog()
            }
        })
        viewModel.itemId.observe(viewLifecycleOwner, EventObserve {
            val action =
                AllSavedListsFragmentDirections.actionAllSavedListsFragmentToSavedListFragment(it)
            findNavController().navigate(action)
        })
    }

    private fun navigateToCreateListDialog() {
        val action = AllSavedListsFragmentDirections.actionAllSavedListsFragmentToCreateListDialog()
        findNavController().navigate(action)
    }
}