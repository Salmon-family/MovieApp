package com.karrar.movieapp.ui.movieDetails.saveMovie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.DialogSaveMovieBinding
import com.karrar.movieapp.ui.base.BaseDialogFragment
import com.karrar.movieapp.ui.movieDetails.MovieDetailsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SaveMovieDialog : BaseDialogFragment<DialogSaveMovieBinding>() {

    override val layoutIdFragment = R.layout.dialog_save_movie
    override val viewModel: SaveMovieViewModel by viewModels()
    private val args: SaveMovieDialogArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveListAdapter.adapter = SaveListAdapter(mutableListOf(), viewModel)

        viewModel.clickListEvent.observe(viewLifecycleOwner){
            viewModel.checkMovie(args.movieId)
        }

        viewModel.message.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }


}