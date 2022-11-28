package com.kudu.sweets_admin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kudu.sweets_admin.activity.ProductDetailsActivity
import com.kudu.sweets_admin.databinding.ItemProductListViewBinding

class SoldProductListAdapter(
    private val context: Context,
    private val soldList: ArrayList<String>
) : RecyclerView.Adapter<SoldProductListAdapter.MyHolder>() {

    class MyHolder(binding: ItemProductListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvProductName
        val price = binding.tvProductPrice
        val delete = binding.ivDeleteProduct
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemProductListViewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val model = soldList[position]

        holder.title.text = model
        holder.delete.visibility = View.GONE
        holder.root.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return soldList.size
    }
}