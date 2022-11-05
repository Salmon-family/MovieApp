package com.thechance.ui.myList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.thechance.ui.R
import com.thechance.ui.base.BaseFragment
import com.thechance.ui.databinding.FragmentMyListsBinding
import com.thechance.viewmodel.myList.MyListsViewModel
import com.thechance.viewmodel.myList.myListUIState.MyListUIEvent
import com.thechance.viewmodel.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyListsFragment : BaseFragment<FragmentMyListsBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_my_lists
    override val viewModel: MyListsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.myList))
        binding.savedList.adapter = CreatedListAdapter(emptyList(), viewModel)
        collectEvent()
    }

    private fun collectEvent() {
        collectLast(viewModel.myListUIEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: MyListUIEvent) {
        var action: NavDirections? = null
        when (event) {
            MyListUIEvent.CreateButtonClicked -> {
                action = MyListsFragmentDirections.actionMyListFragmentToCreateSavedList()
            }
            is MyListUIEvent.DisplayError -> {
                Toast.makeText(requireContext(), event.errorMessage, Toast.LENGTH_LONG).show()
            }
            is MyListUIEvent.OnSelectItem -> {
                action = MyListsFragmentDirections.actionMyListFragmentToSavedListFragment(
                    event.createdListUIState.listID,
                    event.createdListUIState.name
                )
            }
            else -> {
            }
        }
        action?.let { findNavController().navigate(it) }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

}