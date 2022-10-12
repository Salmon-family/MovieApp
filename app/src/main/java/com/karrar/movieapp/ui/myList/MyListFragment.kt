package com.karrar.movieapp.ui.myList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentMyListBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListFragment : BaseFragment<FragmentMyListBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_my_list
    override val viewModel: MyListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true)
        binding.savedList.adapter = CreatedListAdapter(emptyList(), viewModel)
        observeEvents()

    }

    private fun observeEvents() {
        viewModel.isButtonClicked.observeEvent(viewLifecycleOwner) {
            navigateToCreateListDialog()
        }
        viewModel.itemId.observe(viewLifecycleOwner, EventObserve {
            val action =
                MyListFragmentDirections.actionMyListFragmentToSavedListFragment(it)
            findNavController().navigate(action)
        })

    }

    private fun navigateToCreateListDialog() {
        findNavController().navigate(
            MyListFragmentDirections.actionMyListFragmentToCreateSavedList()
        )
    }
}