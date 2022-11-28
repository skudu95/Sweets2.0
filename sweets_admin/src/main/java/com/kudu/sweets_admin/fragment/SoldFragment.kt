package com.kudu.sweets_admin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudu.sweets_admin.R
import com.kudu.sweets_admin.adapter.SoldProductListAdapter
import com.kudu.sweets_admin.databinding.FragmentSoldBinding

class SoldFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sold, container, false)
        val binding = FragmentSoldBinding.bind(view)

        (activity as AppCompatActivity).supportActionBar?.title = "Sold Products"

        val tempList = ArrayList<String>()
        tempList.add("Sold Product 1")
        tempList.add("Sold Product 2")
        tempList.add("Sold Product 3")
        tempList.add("Sold Product 4")
        tempList.add("Sold Product 5")
        tempList.add("Sold Product 6")
        tempList.add("Sold Product 7")
        tempList.add("Sold Product 8")
        tempList.add("Sold Product 9")
        tempList.add("Sold Product 10")
        tempList.add("Sold Product 11")
        tempList.add("Sold Product 12")
        tempList.add("Sold Product 13")
        tempList.add("Sold Product 14")
        tempList.add("Sold Product 15")
        tempList.add("Sold Product 16")
        tempList.add("Sold Product 17")
        tempList.add("Sold Product 18")
        tempList.add("Sold Product 19")
        tempList.add("Sold Product 20")
        tempList.add("Sold Product 21")
        tempList.add("Sold Product 22")
        tempList.add("Sold Product 23")
        tempList.add("Sold Product 24")
        tempList.add("Sold Product 25")
        tempList.add("Sold Product 26")
        tempList.add("Sold Product 27")
        tempList.add("Sold Product 28")
        tempList.add("Sold Product 29")
        tempList.add("Sold Product 30")

        binding.rvSoldItems.setHasFixedSize(true)
        binding.rvSoldItems.setItemViewCacheSize(20)
        binding.rvSoldItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSoldItems.adapter = SoldProductListAdapter(requireContext(), tempList)


        return view
    }

}