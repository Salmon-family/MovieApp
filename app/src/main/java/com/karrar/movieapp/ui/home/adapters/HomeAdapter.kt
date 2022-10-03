package com.karrar.movieapp.ui.home.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.BR
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseDiffUtil
import com.karrar.movieapp.ui.home.HomeInteractionListener
import com.karrar.movieapp.ui.home.HomeRecyclerItem

class HomeAdapter(
    private val items: MutableList<HomeRecyclerItem>,
    private val listener: HomeInteractionListener,
) : BaseAdapter<HomeRecyclerItem>(items, listener) {
    override var layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutID = viewType
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder as ItemViewHolder, position)
    }

    override fun bind(holder: ItemViewHolder, position: Int) {
        when (val currentItem = items[position]) {
            is HomeRecyclerItem.Slider -> {
                holder.binding.setVariable(BR.adapterRecycler,
                    PopularMovieAdapter(currentItem.items, listener))
            }

            is HomeRecyclerItem.TvShows -> {
                holder.binding.run {
                    setVariable(BR.topRated, currentItem.items.first())
                    setVariable(BR.latest, currentItem.items[1])
                    setVariable(BR.latest, currentItem.items.last())
                }
            }
            is HomeRecyclerItem.Movie -> {
                holder.binding.run {
                    setVariable(BR.adapterRecycler, MovieAdapter(currentItem.items, listener))
                    setVariable(BR.movieType, currentItem.type)
                }

            }
            is HomeRecyclerItem.Actor -> {
                holder.binding.setVariable(BR.adapterRecycler, ActorAdapter(currentItem.items, listener))
            }
            is HomeRecyclerItem.AiringToday -> {
                holder.binding.run {
                    setVariable(BR.adapterRecycler,
                        AiringTodayAdapter(currentItem.items.take(6), listener))
                    setVariable(BR.count,currentItem.items.size)
                }


            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(newItem: HomeRecyclerItem) {
        val newItems = items.apply {
            add(newItem)
            sortBy {
                it.priority
            }
        }
        val diffResult = DiffUtil.calculateDiff(BaseDiffUtil(items, newItems,::areItemsSame, ::areContentSame))
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    override fun areItemsSame(oldItem: HomeRecyclerItem, newItem: HomeRecyclerItem): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun getItemViewType(position: Int) = items[position].layoutID


}


