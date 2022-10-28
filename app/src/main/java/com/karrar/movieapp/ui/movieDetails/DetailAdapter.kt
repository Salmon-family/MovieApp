package com.karrar.movieapp.ui.movieDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.karrar.movieapp.BR
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.adapters.*
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.DetailItemUIState

class DetailAdapter(
    private var items: List<DetailItemUIState>,
    private val listener: BaseInteractionListener,
) : BaseAdapter<DetailItemUIState>(items, listener) {
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
            is DetailItemUIState.Header -> {
                holder.binding.run {
                    setVariable(BR.item, currentItem.data)
                    setVariable(BR.listener, listener as DetailInteractionListener)
                }
            }
            is DetailItemUIState.Cast -> {
                holder.binding.run {
                    setVariable(
                        BR.adapterRecycler,
                        ActorAdapter2(
                            currentItem.data,
                            R.layout.item_cast_2,
                            listener as ActorsInteractionListener
                        )
                    )
                }
            }
            is DetailItemUIState.SimilarMovies -> {
                holder.binding.run {
                    setVariable(
                        BR.adapterRecycler,
                        MovieAdapter2(currentItem.data, listener as MovieInteractionListener)
                    )
                }
            }
            is DetailItemUIState.Rating -> {
                holder.binding.run {
                    setVariable(BR.viewModel, currentItem.viewModel)
                }
            }
            is DetailItemUIState.Comment -> {
                holder.binding.run {
                    setVariable(BR.item, currentItem.data)
                    setVariable(BR.listener, listener)
                }
            }
            is DetailItemUIState.ReviewText -> {}
            DetailItemUIState.SeeAllReviewsButton -> {
                holder.binding.run {
                    setVariable(BR.listener, listener as DetailInteractionListener)
                }
            }
        }
    }

    override fun setItems(newItems: List<DetailItemUIState>) {
        items = newItems.sortedBy { it.priority }
        super.setItems(items)
    }

    override fun areItemsSame(oldItem: DetailItemUIState, newItem: DetailItemUIState): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DetailItemUIState.Header -> R.layout.item_movie_detail_header
            is DetailItemUIState.Cast -> R.layout.list_cast_2
            is DetailItemUIState.SimilarMovies -> R.layout.list_similar_movie_2
            is DetailItemUIState.Rating -> R.layout.item_rating
            is DetailItemUIState.Comment -> R.layout.item_movie_review
            is DetailItemUIState.ReviewText -> R.layout.item_review_text
            DetailItemUIState.SeeAllReviewsButton -> R.layout.item_see_all_reviews
        }
    }

}


