package com.example.profileapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.profileapp.R
import com.example.profileapp.databinding.LayoutItemPeopleBinding
import com.example.profileapp.domain.model.CountriesItem
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class MainListAdapter @Inject constructor() : ListAdapter<CountriesItem, MainListAdapter.ViewHolder>(
    ListDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    inner class ViewHolder internal constructor(private val binding: LayoutItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: CountriesItem) = with(itemView) {

            Glide.with(context)
                .load(result.flag)
                .placeholder(R.drawable.animate_loading)
                .error(R.drawable.error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(binding.circleIV)
            binding.result = result
            binding.executePendingBindings()
        }
    }
}

class ListDiffCallback : DiffUtil.ItemCallback<CountriesItem>() {

    override fun areItemsTheSame(
        oldItem: CountriesItem,
        newItem: CountriesItem
    ): Boolean = oldItem.code == newItem.code

    override fun areContentsTheSame(
        oldItem: CountriesItem,
        newItem: CountriesItem
    ): Boolean = oldItem == newItem
}