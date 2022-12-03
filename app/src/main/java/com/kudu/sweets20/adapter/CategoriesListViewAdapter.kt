package com.kudu.sweets20.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kudu.sweets20.databinding.ItemCategoriesViewBinding

class CategoriesListViewAdapter(
    private val context: Context,
    private val categoryList: ArrayList<String>
) : RecyclerView.Adapter<CategoriesListViewAdapter.MyHolder>() {

    class MyHolder(binding: ItemCategoriesViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvCategoryTitle
        val image = binding.ivCategoryImage
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemCategoriesViewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val model = categoryList[position]

        holder.title.text = model
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}