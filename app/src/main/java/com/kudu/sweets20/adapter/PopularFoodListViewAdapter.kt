package com.kudu.sweets20.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kudu.sweets20.databinding.ItemPopularsViewBinding

class PopularFoodListViewAdapter(
    private val context: Context,
    private val popularList: ArrayList<String>
) : RecyclerView.Adapter<PopularFoodListViewAdapter.MyHolder>() {

    class MyHolder(binding: ItemPopularsViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvFoodNamePopular
        val extra = binding.tvFoodExtraPopular
        val image = binding.ivPopularImage
        val rating = binding.tvRatingPopular
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemPopularsViewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val model = popularList[position]

        holder.title.text = model
    }

    override fun getItemCount(): Int {
        return popularList.size
    }
}