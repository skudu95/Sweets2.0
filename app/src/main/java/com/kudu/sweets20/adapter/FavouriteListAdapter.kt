package com.kudu.sweets20.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kudu.common.model.Products
import com.kudu.common.util.GlideLoader
import com.kudu.sweets20.R
import com.kudu.sweets20.activity.ProductDetailsActivity
import com.kudu.sweets20.databinding.ItemPopularsViewBinding

class FavouriteListAdapter(
    private val context: Context,
    private val favouriteList: ArrayList<Products>
) : RecyclerView.Adapter<FavouriteListAdapter.MyHolder>() {

    //TODO: change the favourite item view
    class MyHolder(binding: ItemPopularsViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvFoodNamePopular
        val extra = binding.tvFoodExtraPopular
        val image = binding.ivPopularImage
        val rating = binding.tvRatingPopular
        val favourite = binding.btnFavouriteFood
        val price = binding.tvFoodPricePopular
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
        val model = favouriteList[position]

        holder.title.text = model.title
        holder.extra.visibility = View.GONE
        GlideLoader(context).loadProductPicture(model.image, holder.image)
        holder.rating.visibility = View.GONE
        //TODO: make changes accordingly
        holder.favourite.setImageResource(R.drawable.favourite_filled)
        holder.favourite.setOnClickListener {
            holder.favourite.setImageResource(R.drawable.favourite_empty_2)
        }
        holder.price.text = buildString {
            append("$ ")
            append(model.price)
        }
        holder.root.setOnClickListener {
            Toast.makeText(context, "${model.title} clicked", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, ProductDetailsActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }
}