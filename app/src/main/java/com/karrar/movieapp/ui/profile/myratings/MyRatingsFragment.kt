package com.karrar.movieapp.ui.profile.myratings


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentMyRatingsBinding
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.utilities.EventObserve
import com.karrar.movieapp.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRatingsFragment : BaseFragment<FragmentMyRatingsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_my_ratings
    override val viewModel: MyRatingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RatedMoviesAdapter(emptyList(), viewModel)
        binding.recyclerViewRatedMovies.adapter = adapter
        observeEvents()
        setTitle(true, getString(R.string.my_ratings))
    }

    private fun observeEvents() {
        viewModel.clickMovieEvent.observeEvent(viewLifecycleOwner){ movieId ->
            findNavController().navigate(MyRatingsFragmentDirections.actionRatedMoviesFragmentToMovieDetailFragment(movieId))
        }
    }
}