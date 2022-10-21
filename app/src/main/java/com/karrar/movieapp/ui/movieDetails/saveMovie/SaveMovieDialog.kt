package com.karrar.movieapp.ui.movieDetails.saveMovie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.DialogSaveMovieBinding
import com.karrar.movieapp.ui.base.BaseDialogFragment
import com.karrar.movieapp.ui.myList.MyListsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SaveMovieDialog : BaseDialogFragment<DialogSaveMovieBinding>() {

    override val layoutIdFragment = R.layout.dialog_save_movie
    override val viewModel: MyListsViewModel by activityViewModels()
    private val args: SaveMovieDialogArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveListAdapter.adapter = SaveListAdapter(mutableListOf(), viewModel)

        viewModel.newAdd.observe(viewLifecycleOwner){
            if(it == true) viewModel.checkMovie(args.movieId)
        }

        viewModel.message.observe(viewLifecycleOwner) {
            if (viewModel.newAdd.value == true){
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }

    }



}