package com.kudu.sweets20.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudu.sweets20.R
import com.kudu.sweets20.adapter.CartItemsListAdapter
import com.kudu.sweets20.databinding.FragmentCartItemsBinding

class CartItemsFragment : Fragment() {

    private lateinit var binding: FragmentCartItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart_items, container, false)
        binding = FragmentCartItemsBinding.bind(view)

        val tempList: ArrayList<String> = ArrayList()

        tempList.add("Cart Item 1")
        tempList.add("Cart Item 2")
        tempList.add("Cart Item 3")
        tempList.add("Cart Item 4")
        tempList.add("Cart Item 5")
        tempList.add("Cart Item 6")
        tempList.add("Cart Item 7")

        binding.rvCartItems.setHasFixedSize(true)
        binding.rvCartItems.setItemViewCacheSize(10)
        binding.rvCartItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCartItems.adapter = CartItemsListAdapter(requireContext(), tempList)

        return view
    }

}