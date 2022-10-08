package com.karrar.movieapp.ui.movieDetails

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.BR
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.adapters.ActorAdapter
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MovieAdapter
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseDiffUtil
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.movieReviews.ReviewAdapter

class DetailAdapter(
    private val items: MutableList<DetailItem>,
    private val listener: BaseInteractionListener,
) : BaseAdapter<DetailItem>(items, listener) {
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
            is DetailItem.Header -> {
                holder.binding.run {
                    setVariable(BR.item, currentItem.data)
                    setVariable(BR.listener, listener as DetailInteractionListener)
                }
            }
            is DetailItem.Cast -> {
                holder.binding.run {
                    setVariable(BR.adapterRecycler,ActorAdapter(currentItem.data,R.layout.item_cast,listener as ActorsInteractionListener))
                }
            }
            is DetailItem.SimilarMovies -> {
                holder.binding.run {
                    setVariable(BR.adapterRecycler,MovieAdapter(currentItem.data,listener as MovieInteractionListener))
                }
            }
            is DetailItem.Rating -> {
                holder.binding.run {
                    setVariable(BR.viewModel, currentItem.viewModel)
                }
            }
            is DetailItem.Comment -> {
                holder.binding.run {
                    setVariable(BR.adapterRecycler,ReviewAdapter(currentItem.data,listener as DetailInteractionListener))
                    setVariable(BR.listener, listener)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(newItem: DetailItem) {
        val newItems = items.apply {
            add(newItem)
            sortBy {
                it.priority
            }
        }
        val diffResult =
            DiffUtil.calculateDiff(BaseDiffUtil(items, newItems, ::areItemsSame, ::areContentSame))
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    override fun areItemsSame(oldItem: DetailItem, newItem: DetailItem): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DetailItem.Header -> R.layout.item_movei_detail_header
            is DetailItem.Cast -> R.layout.list_cast
            is DetailItem.SimilarMovies -> R.layout.list_similar_movie
            is DetailItem.Rating -> R.layout.item_rating
            is DetailItem.Comment -> R.layout.list_reviews_comment
        }
    }

}


