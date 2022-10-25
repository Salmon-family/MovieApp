package com.karrar.movieapp.ui.movieDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.karrar.movieapp.BR
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.adapters.ActorAdapter
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.adapters.MovieAdapter
import com.karrar.movieapp.ui.adapters.MovieInteractionListener
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.tvShowDetails.SeasonAdapter
import com.karrar.movieapp.ui.tvShowDetails.SeasonInteractionListener

class DetailAdapter(
    private var items: List<DetailItem>,
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
                    setVariable(BR.adapterRecycler,
                        ActorAdapter(currentItem.data,
                            R.layout.item_cast,
                            listener as ActorsInteractionListener))
                }
            }
            is DetailItem.SimilarMovies -> {
                holder.binding.run {
                    setVariable(BR.adapterRecycler,
                        MovieAdapter(currentItem.data, listener as MovieInteractionListener))
                }
            }
            is DetailItem.Seasons -> {
                holder.binding.run {
                    setVariable(BR.adapterRecycler,
                        SeasonAdapter(currentItem.data, listener as SeasonInteractionListener)
                    )
                }
            }
            is DetailItem.Rating -> {
                holder.binding.run {
                    setVariable(BR.viewModel, currentItem.viewModel)
                }
            }
            is DetailItem.Comment -> {
                holder.binding.run {
                    setVariable(BR.item, currentItem.data)
                    setVariable(BR.listener, listener)
                }
            }
            is DetailItem.ReviewText -> {}
            DetailItem.SeeAllReviewsButton -> {
                holder.binding.run {
                    setVariable(BR.listener, listener as DetailInteractionListener)
                }
            }
            else -> {}
        }
    }

    override fun setItems(newItems: List<DetailItem>) {
        items = newItems.sortedBy { it.priority }
        super.setItems(items)
    }

    override fun areItemsSame(oldItem: DetailItem, newItem: DetailItem): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DetailItem.Header -> R.layout.item_movei_detail_header
            is DetailItem.Cast -> R.layout.list_cast
            is DetailItem.SimilarMovies -> R.layout.list_similar_movie
            is DetailItem.Seasons -> R.layout.list_season
            is DetailItem.Rating -> R.layout.item_rating
            is DetailItem.Comment -> R.layout.item_movie_review
            is DetailItem.ReviewText -> R.layout.item_review_text
            DetailItem.SeeAllReviewsButton -> R.layout.item_see_all_reviews
            else -> 0
        }
    }

}


