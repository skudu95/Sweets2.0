package com.kudu.sweets_admin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kudu.common.model.Products
import com.kudu.common.util.GlideLoader
import com.kudu.sweets_admin.activity.ProductDetailsActivity
import com.kudu.sweets_admin.databinding.ItemProductListViewBinding

class ProductListAdapter(
    private val context: Context,
    private val productList: ArrayList<Products>,
) : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {


    class MyViewHolder(binding: ItemProductListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvProductName
        val price = binding.tvProductPrice
        val image = binding.ivProductImage
        val quantity = binding.tvProductQuantity
        val delete = binding.ivDeleteProduct
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemProductListViewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = productList[position]

        holder.title.text = model.title
        holder.price.text = buildString {
            append("$ ")
            append(model.price)
        }
        holder.quantity.text = buildString {
            append(model.stock_quantity)
            append(" available")
        }
        GlideLoader(context).loadProductPicture(model.image, holder.image)
        holder.delete.setOnClickListener {
            Toast.makeText(context, "delete clicked", Toast.LENGTH_SHORT).show()
        }
        holder.root.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}