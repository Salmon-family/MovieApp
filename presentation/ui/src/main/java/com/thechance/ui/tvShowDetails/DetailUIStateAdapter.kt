package com.thechance.ui.tvShowDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.thechance.ui.BR
import com.thechance.ui.R
import com.thechance.ui.adapters.ActorAdapter
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.base.BaseInteractionListener
import com.thechance.viewmodel.category.com.thechance.viewmodel.tvShowDetails.episodes.SeasonInteractionListener
import com.thechance.viewmodel.movieDetails.ActorsInteractionListener
import com.thechance.viewmodel.movieDetails.DetailInteractionListener
import com.thechance.viewmodel.tvShowDetails.tvShowUIState.DetailItemUIState

class DetailUIStateAdapter(
    private var items: List<DetailItemUIState>,
    private val listener: BaseInteractionListener
) : BaseAdapter<DetailItemUIState>(items, listener) {
    override val layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
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
                        ActorAdapter(
                            currentItem.data,
                            R.layout.item_cast,
                            listener as ActorsInteractionListener
                        )
                    )
                }
            }
            is DetailItemUIState.Seasons -> {
                holder.binding.run {
                    setVariable(
                        BR.adapterRecycler,
                        SeasonAdapterUIState(
                            currentItem.data,
                            listener as SeasonInteractionListener
                        )
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
            is DetailItemUIState.Header -> R.layout.item_tv_show_details_header
            is DetailItemUIState.Cast -> R.layout.list_cast
            is DetailItemUIState.Seasons -> R.layout.list_season
            is DetailItemUIState.Rating -> R.layout.item_tvshow_rating
            is DetailItemUIState.Comment -> R.layout.item_tvshow_review
            is DetailItemUIState.ReviewText -> R.layout.item_review_text
            DetailItemUIState.SeeAllReviewsButton -> R.layout.item_see_all_reviews
        }
    }
}
