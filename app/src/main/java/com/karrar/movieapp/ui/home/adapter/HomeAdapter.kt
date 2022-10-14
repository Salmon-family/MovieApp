package com.karrar.movieapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.karrar.movieapp.BR
import com.karrar.movieapp.R
import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.adapters.*
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.home.HomeInteractionListener
import com.karrar.movieapp.ui.home.HomeRecyclerItem
import com.karrar.movieapp.utilities.Constants

class HomeAdapter(
    private var homeItems: List<HomeRecyclerItem>,
    private val listener: BaseInteractionListener,
) : BaseAdapter<HomeRecyclerItem>(homeItems, listener) {
    override val layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), viewType, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (homeItems.isNotEmpty())
            bind(holder as ItemViewHolder, position)
    }

    override fun bind(holder: ItemViewHolder, position: Int) {
        if (position != -1)
            when (val currentItem = homeItems[position]) {
                is HomeRecyclerItem.Slider -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        PopularMovieAdapter(currentItem.items, listener as HomeInteractionListener)
                    )
                }

                is HomeRecyclerItem.TvShows -> {
                    holder.binding.run {
                        setVariable(BR.topRated, currentItem.items.first())
                        setVariable(BR.popular, currentItem.items[1])
                        setVariable(BR.latest, currentItem.items.last())
                        setVariable(BR.listener, listener as TVShowInteractionListener)
                    }
                }

                is HomeRecyclerItem.Actor -> {
                    holder.binding.run {
                        setVariable(
                            BR.adapterRecycler, ActorAdapter(
                                currentItem.items,
                                R.layout.item_actor,
                                listener as ActorsInteractionListener
                            )
                        )
                        setVariable(BR.listener, listener as HomeInteractionListener)
                    }

                }

                is HomeRecyclerItem.AiringToday -> {
                    holder.binding.run {
                        setVariable(
                            BR.adapterRecycler,
                            MediaAdapter(
                                currentItem.items.take(Constants.MAX_NUMBER_AIRING_TODAY),
                                R.layout.item_airing_today,
                                listener as MediaInteractionListener
                            )
                        )
                        setVariable(BR.count, currentItem.items.size)
                    }
                }

                is HomeRecyclerItem.Adventure -> {
                    bindMovie(holder, currentItem.items, currentItem.type)
                }

                is HomeRecyclerItem.Mystery -> {
                    bindMovie(holder, currentItem.items, currentItem.type)
                }

                is HomeRecyclerItem.NowStreaming -> {
                    bindMovie(holder, currentItem.items, currentItem.type)
                }

                is HomeRecyclerItem.OnTheAiring -> {
                    holder.binding.run {
                        setVariable(
                            BR.adapterRecycler,
                            TVShowAdapter(currentItem.items, listener as TVShowInteractionListener)
                        )
                        setVariable(BR.movieType, currentItem.type)
                    }
                }

                is HomeRecyclerItem.Trending -> {
                    bindMovie(holder, currentItem.items, currentItem.type)
                }

                is HomeRecyclerItem.Upcoming -> {
                    bindMovie(holder, currentItem.items, currentItem.type)
                }
            }
    }

    private fun bindMovie(holder: ItemViewHolder, items: List<Media>, type: HomeItemsType) {
        holder.binding.run {
            setVariable(
                BR.adapterRecycler,
                MovieAdapter(items, listener as MovieInteractionListener)
            )
            setVariable(BR.movieType, type)
        }
    }

    override fun setItems(newItems: List<HomeRecyclerItem>) {
        homeItems = newItems.sortedBy { it.priority }
        super.setItems(homeItems)
    }

    override fun areItemsSame(oldItem: HomeRecyclerItem, newItem: HomeRecyclerItem): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun getItemViewType(position: Int): Int {
        if (homeItems.isNotEmpty()) {
            return when (homeItems[position]) {
                is HomeRecyclerItem.Actor -> R.layout.list_actor
                is HomeRecyclerItem.TvShows -> R.layout.list_tv_shows
                is HomeRecyclerItem.Slider -> R.layout.list_popular
                is HomeRecyclerItem.AiringToday -> R.layout.list_airing_today
                is HomeRecyclerItem.OnTheAiring -> R.layout.list_tvshow
                is HomeRecyclerItem.Adventure,
                is HomeRecyclerItem.Mystery,
                is HomeRecyclerItem.NowStreaming,
                is HomeRecyclerItem.Trending,
                is HomeRecyclerItem.Upcoming,
                -> R.layout.list_movie
            }
        }
        return -1
    }

}

