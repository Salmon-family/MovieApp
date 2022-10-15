package com.karrar.movieapp.ui.myList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentMyListsBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.Constants
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListsFragment : BaseFragment<FragmentMyListsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_my_lists
    override val viewModel: MyListsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true,getString(R.string.myList))
        binding.savedList.adapter = CreatedListAdapter(emptyList(), viewModel)
        observeEvents()

        setFragmentResultListener(Constants.REQUEST_KEY) { _, bundle ->
            val isUpdated = bundle.getBoolean(Constants.BUNDLE_KEY)
            if (isUpdated) viewModel.getData()
        }

    }

    private fun observeEvents() {
        viewModel.isButtonClicked.observeEvent(viewLifecycleOwner) {
            navigateToCreateListDialog()
        }

        viewModel.item.observeEvent(viewLifecycleOwner){
            val action = MyListsFragmentDirections.actionMyListFragmentToSavedListFragment(it.id, it.name)
            findNavController().navigate(action)
        }
    }

    private fun navigateToCreateListDialog() {
        findNavController().navigate(
            MyListsFragmentDirections.actionMyListFragmentToCreateSavedList()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }
}