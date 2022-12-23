package com.kudu.sweets20.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kudu.sweets20.R
import com.kudu.sweets20.databinding.ItemCartItemBinding

class CartItemsListAdapter(private val context: Context, private val itemsList: ArrayList<String>) :
    RecyclerView.Adapter<CartItemsListAdapter.MyHolder>() {

    class MyHolder(binding: ItemCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvCartItemProductName
        val image = binding.ivCartItemProductImage
        val price = binding.tvCartItemProductPrice
        val dropdown = binding.dropdownCartItemNumber
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ItemCartItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val model = itemsList[position]

        holder.title.text = model

        val itemNumberList =
            context.resources.getStringArray(com.kudu.common.R.array.item_number_list)
        val itemNumberAdapter =
            ArrayAdapter(context, R.layout.item_dropdown_cart_item_number_list, itemNumberList)
        holder.dropdown.setAdapter(itemNumberAdapter)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}