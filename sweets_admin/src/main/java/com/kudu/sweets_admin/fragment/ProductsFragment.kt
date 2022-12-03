package com.kudu.sweets_admin.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudu.sweets_admin.R
import com.kudu.sweets_admin.activity.AddProductActivity
import com.kudu.sweets_admin.adapter.ProductListAdapter
import com.kudu.sweets_admin.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        val binding = FragmentProductsBinding.bind(view)

        (activity as AppCompatActivity).supportActionBar?.title = "Products"

        val tempList = ArrayList<String>()
        tempList.add("Product 1")
        tempList.add("Product 2")
        tempList.add("Product 3")
        tempList.add("Product 4")
        tempList.add("Product 5")
        tempList.add("Product 6")
        tempList.add("Product 7")
        tempList.add("Product 8")
        tempList.add("Product 9")
        tempList.add("Product 10")
        tempList.add("Product 11")
        tempList.add("Product 12")
        tempList.add("Product 13")
        tempList.add("Product 14")
        tempList.add("Product 15")
        tempList.add("Product 16")
        tempList.add("Product 17")
        tempList.add("Product 18")
        tempList.add("Product 19")
        tempList.add("Product 20")
        tempList.add("Product 21")
        tempList.add("Product 22")
        tempList.add("Product 23")
        tempList.add("Product 24")
        tempList.add("Product 25")
        tempList.add("Product 26")
        tempList.add("Product 27")
        tempList.add("Product 28")
        tempList.add("Product 29")
        tempList.add("Product 30")

        binding.rvProducts.setHasFixedSize(true)
        binding.rvProducts.setItemViewCacheSize(20)
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.adapter = ProductListAdapter(requireContext(), tempList)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_product_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_product -> {
                startActivity(Intent(activity, AddProductActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}