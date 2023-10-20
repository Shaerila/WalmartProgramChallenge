package com.mindorks.retrofit.walmartprogramchallenge.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mindorks.retrofit.walmartprogramchallenge.databinding.CardViewBinding
import com.mindorks.retrofit.walmartprogramchallenge.model.CountryInfoItem

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    // Cardview
    inner class CountryViewHolder(val binding: CardViewBinding) : ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<CountryInfoItem>() {

        override fun areItemsTheSame(oldItem: CountryInfoItem, newItem: CountryInfoItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CountryInfoItem,
            newItem: CountryInfoItem
        ): Boolean {
            return oldItem == newItem
        }
    }
        private val differ = AsyncListDiffer(this, diffCallback)
        var countryInfoItem: List<CountryInfoItem>
            get() = differ.currentList
            set(value) { differ.submitList(value) }

        override fun getItemCount() = countryInfoItem.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
            return CountryViewHolder(CardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.apply {
            val countryInfoItem = countryInfoItem[position]
            textviewName.text = countryInfoItem.name
            textviewCapital.text = countryInfoItem.capital
            textviewCode.text = countryInfoItem.code
            textviewRegion.text = countryInfoItem.region
        }
    }
}