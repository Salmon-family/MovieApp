package com.karrar.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.databinding.ItemLoadStateBinding
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class MediaLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MediaLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadStateViewHolder(ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)


    inner class LoadStateViewHolder(val binding: ItemLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }
        fun bind(loadState: LoadState) {
            binding.apply {
                progress.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState is LoadState.Error
                textInternetConnection.isVisible = loadState is LoadState.Error

            }
        }
    }
}
