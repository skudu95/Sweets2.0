package com.kudu.sweets20.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kudu.sweets20.R
import com.kudu.sweets20.databinding.FragmentCartItemsBinding

class CartItemsFragment : Fragment() {

    private lateinit var binding: FragmentCartItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart_items, container, false)
        binding = FragmentCartItemsBinding.bind(view)


        return view
    }

}