package com.example.foodapi.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapi.databinding.FoodItemBinding
import com.example.foodapi.model.Food

class FoodAdapter() : androidx.recyclerview.widget.ListAdapter<Food, FoodAdapter.FoodListViewHolder>(DiffCallBack()) {

    lateinit var onClick: (id: Int) -> Unit
    lateinit var onLongClick: (Int) -> Unit

    private class DiffCallBack : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.foodId == newItem.foodId
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }
    }

    inner class FoodListViewHolder(private val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            with(binding) {
                Glide.with(foodView)
                    .load(food.image)
                    .circleCrop()
                    .into(foodView)
                binding.foodName.text = food.title

            }
            itemView.setOnLongClickListener {
                onLongClick(food.foodId)
                true
            }
            itemView.setOnClickListener {
                onClick(food.foodId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        return FoodListViewHolder(
            FoodItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

