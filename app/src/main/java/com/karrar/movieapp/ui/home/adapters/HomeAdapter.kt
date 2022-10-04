package com.karrar.movieapp.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.BR
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseDiffUtil
import com.karrar.movieapp.ui.home.HomeInteractionListener
import com.karrar.movieapp.ui.home.HomeRecyclerItem

class HomeAdapter(
    private var items: MutableList<HomeRecyclerItem>,
    private val listener: HomeInteractionListener,
) : BaseAdapter<HomeRecyclerItem>(items, listener) {
    override val layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), viewType, parent, false
            )
        )
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

    override fun setItems(newItems: List<HomeRecyclerItem>) {
        val diffResult = DiffUtil.calculateDiff(BaseDiffUtil(items, newItems,::areItemsSame, ::areContentSame))
        items = newItems.toMutableList()
        items.sortBy { it.priority }
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }


    override fun areItemsSame(oldItem: HomeRecyclerItem, newItem: HomeRecyclerItem): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun getItemViewType(position: Int) = items[position].layoutID


}


