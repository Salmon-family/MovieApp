package com.karrar.movieapp.ui.home.adapters

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.BR
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseDiffUtil
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.home.HomeRecyclerItem

class HomeAdapter(
    private val items: MutableList<HomeRecyclerItem>,
    private val listener: HomeInteractionListener,
) : BaseAdapter<HomeRecyclerItem>(items, listener) {
    override var layoutID: Int = 0

    fun addItem(newItems: HomeRecyclerItem) {
        val newItemsList = items.apply {
            add(newItems)
            sortBy {
                it.priority
            }
        }
        val diffResult = DiffUtil.calculateDiff(BaseDiffUtil(items,
            newItemsList,
            ::areItemsSame, ::areContentSame))
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    override fun areItemsSame(oldItem: HomeRecyclerItem, newItem: HomeRecyclerItem): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutID = viewType
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder as ItemViewHolder, position)
    }

    override fun bind(holder: ItemViewHolder, position: Int) {
        when (val currentItem = items[position]) {
            is HomeRecyclerItem.ActorType -> {
                holder.binding.setVariable(BR.adapterRecycler,
                    ActorAdapter(currentItem.items, listener))
            }
            is HomeRecyclerItem.MoviesType -> {

                holder.binding.run {
                    setVariable(BR.adapterRecycler, MovieAdapter(currentItem.items, listener))
                    setVariable(BR.title, currentItem.type.name)
                }

            }
            is HomeRecyclerItem.SlideType -> {
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
        }
    }


    override fun getItemViewType(position: Int) = when (items[position]) {
        is HomeRecyclerItem.ActorType -> R.layout.list_actor
        is HomeRecyclerItem.MoviesType -> R.layout.list_movie
        is HomeRecyclerItem.SlideType -> R.layout.list_slider
        is HomeRecyclerItem.TvShows -> R.layout.list_collection
    }


}
sealed class BaseViewHolder(view: ViewDataBinding) : RecyclerView.ViewHolder(view.root) {
    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)
    class MovieViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)
    class SlideViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)
}

interface HomeInteractionListener : BaseInteractionListener