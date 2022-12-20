package com.kudu.sweets_admin.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.kudu.common.model.Products
import com.kudu.common.util.Constants
import com.kudu.sweets_admin.R
import com.kudu.sweets_admin.activity.AddProductActivity
import com.kudu.sweets_admin.adapter.ProductListAdapter
import com.kudu.sweets_admin.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private val mFireStore = FirebaseFirestore.getInstance()

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

        mFireStore.collection(Constants.PRODUCTS)
            .get()
            .addOnSuccessListener { document ->
                Log.e("ProductList", document.documents.toString())

                val productList: ArrayList<Products> = ArrayList()
                for (i in document.documents) {
                    val product = i.toObject(Products::class.java)
                    product!!.id = i.id
                    productList.add(product)
                }

                binding.rvProducts.setHasFixedSize(true)
                binding.rvProducts.setItemViewCacheSize(20)
                binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
                binding.rvProducts.adapter = ProductListAdapter(requireContext(), productList)
            }
            .addOnFailureListener { e ->
                Log.e("ProductListError", "Error while fetching product list", e)
            }

        /*binding.rvProducts.setHasFixedSize(true)
        binding.rvProducts.setItemViewCacheSize(20)
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.adapter = ProductListAdapter(requireContext(), tempList)*/

        return view
    }

    private fun getProductList() {
        mFireStore.collection(Constants.PRODUCTS)
            .get()
            .addOnSuccessListener { document ->
                Log.e("ProductList", document.documents.toString())

                val productList: ArrayList<Products> = ArrayList()
                for (i in document.documents) {
                    val product = i.toObject(Products::class.java)
                    product!!.id = i.id
                    productList.add(product)
                }
            }
            .addOnFailureListener { e ->
                Log.e("ProductListError", "Error while fetching product list", e)
            }
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

    override fun onResume() {
        super.onResume()
        getProductList()
    }

}