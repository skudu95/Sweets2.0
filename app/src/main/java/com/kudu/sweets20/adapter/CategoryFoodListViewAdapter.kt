package com.kudu.sweets20.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kudu.common.model.Products
import com.kudu.common.util.GlideLoader
import com.kudu.sweets20.databinding.ItemCategoryFoodBinding

class CategoryFoodListViewAdapter(
    private val context: Context,
    private val foodList: ArrayList<Products>
) : RecyclerView.Adapter<CategoryFoodListViewAdapter.MyHolder>() {

    class MyHolder(binding: ItemCategoryFoodBinding) : RecyclerView.ViewHolder(binding.root) {

        val title = binding.tvProductNameCategoryFood
        val image = binding.ivProductImageCategoryFood
        val price = binding.tvProductPriceCategoryFood
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemCategoryFoodBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val model = foodList[position]

        holder.title.text = model.title
        holder.price.text = buildString {
            append("$ ")
            append(model.price)
        }
        GlideLoader(context).loadProductPicture(model.image, holder.image)
        holder.root.setOnClickListener {
            Toast.makeText(context, "Work to be done", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}