package com.karrar.movieapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ConcatAdapter
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.FragmentHomeBinding
import com.karrar.movieapp.domain.enums.Type
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.base.BaseFragment
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.home.adapters.HomeAdapter
import com.karrar.movieapp.ui.home.adapters.HomeInteractionListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(),HomeInteractionListener {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    val homeItems = mutableListOf<HomeRecyclerItem>(
        HomeRecyclerItem.MoviesType(listOf(
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
        ),Type.OnTheAirType),
        HomeRecyclerItem.MoviesType(listOf(
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
        ),Type.AiringTodayType),
        HomeRecyclerItem.MoviesType(listOf(
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/tVxDe01Zy3kZqaZRNiXFGDICdZk.jpg"),
        ),Type.Adventure),
        HomeRecyclerItem.MoviesType(listOf(
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
            Media(12,"https://image.tmdb.org/t/p/w500/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg"),
        ),Type.Adventure),
    )
    private val homeAdapter = HomeAdapter(mutableListOf(),this)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = homeAdapter

        assignAdapter()
    }


    private fun assignAdapter(){



        viewModel.run {
            trending.observe(viewLifecycleOwner){
                it.toData()?.let { items->
                    homeAdapter.addItem(HomeRecyclerItem.MoviesType(items,Type.Trending))
                }
            }
            upcoming.observe(viewLifecycleOwner){
                it.toData()?.let { items->
                    homeAdapter.addItem(HomeRecyclerItem.MoviesType(items,Type.Upcoming).apply { priority = 2 })
                }
            }
            mysteryMovie.observe(viewLifecycleOwner){
                it.toData()?.let { items->
                    homeAdapter.addItem(HomeRecyclerItem.MoviesType(items,Type.Mystery))
                }
            }

popularTvShow.observe(viewLifecycleOwner){
    it.toData()?.let { items->
        homeAdapter.addItem(HomeRecyclerItem.TvShows(items))
    }
}
            popularMovie.observe(viewLifecycleOwner){
                it.toData()?.let {items->
                    homeAdapter.addItem(HomeRecyclerItem.SlideType(items))
                }
            }

        }

    }
}