package com.kudu.sweets20.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kudu.sweets20.R
import com.kudu.sweets20.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)
        binding = FragmentFavouriteBinding.bind(view)

        setUpActionBar()

        return view
    }

    //setting up actionbar with back button
    private fun setUpActionBar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarFavouriteFragment)

        val actionbar = (activity as AppCompatActivity).supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.back_logo_color)
        }
        binding.toolbarFavouriteFragment.setNavigationOnClickListener { (activity as AppCompatActivity).onBackPressed() }
    }
}