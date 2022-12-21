package com.kudu.sweets20.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kudu.common.model.Products
import com.kudu.common.util.Constants
import com.kudu.common.util.GlideLoader
import com.kudu.sweets20.R
import com.kudu.sweets20.activity.ProductDetailsActivity
import com.kudu.sweets20.databinding.ItemPopularsViewBinding

class PopularFoodListViewAdapter(
    private val context: Context,
    private val popularList: ArrayList<Products>
) : RecyclerView.Adapter<PopularFoodListViewAdapter.MyHolder>() {

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
        val model = popularList[position]

        holder.title.text = model.title
        holder.extra.visibility = View.GONE
        GlideLoader(context).loadProductPicture(model.image, holder.image)
        holder.rating.text = "4.9"
        holder.price.text = buildString {
            append("$ ")
            append(model.price)
        }
        holder.favourite.setOnClickListener {
            Toast.makeText(context, "${model.title} added to Favourites", Toast.LENGTH_SHORT).show()
            holder.favourite.setImageResource(R.drawable.favourite_filled)
        }

        holder.root.setOnClickListener {
//            Toast.makeText(context, "${model.title} clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA_PRODUCT_ID, model.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

    private fun removeFavourite(position: Int) {
        popularList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, popularList.size)
    }
}