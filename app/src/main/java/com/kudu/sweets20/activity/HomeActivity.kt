package com.kudu.sweets20.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.kudu.common.model.Categories
import com.kudu.common.model.Products
import com.kudu.common.util.Constants
import com.kudu.sweets20.R
import com.kudu.sweets20.adapter.CategoriesListViewAdapter
import com.kudu.sweets20.adapter.PopularFoodListViewAdapter
import com.kudu.sweets20.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val mFireStore = FirebaseFirestore.getInstance()
    private val productList: ArrayList<Products> = ArrayList()
    private val isFavourite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryList: ArrayList<Categories> = ArrayList()

        categoryList.add(Categories("Fruit Cake", resources.getDrawable(R.drawable.cake)))
        categoryList.add(Categories("Donuts", resources.getDrawable(R.drawable.donuts)))
        categoryList.add(Categories("French Desserts", resources.getDrawable(R.drawable.desserts)))
        categoryList.add(Categories("Pizza", resources.getDrawable(R.drawable.pizza)))
        categoryList.add(Categories("Burgers", resources.getDrawable(R.drawable.burger)))

//category recyclerview
        binding.rvCategories.setHasFixedSize(true)
        binding.rvCategories.setItemViewCacheSize(20)
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = CategoriesListViewAdapter(this, categoryList)

//popular item list
        binding.rvPopulars.setHasFixedSize(true)
        binding.rvPopulars.setItemViewCacheSize(20)
        binding.rvPopulars.layoutManager = LinearLayoutManager(this)
        binding.rvPopulars.adapter = PopularFoodListViewAdapter(this, productList)

//order now button
        binding.btnOrderNow.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        //profile button
        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun getProductList() {
        mFireStore.collection(Constants.PRODUCTS)
            .limit(5)
            .get()
            .addOnSuccessListener { document ->
                Log.e("ProductList", document.documents.toString())

//                val productList: ArrayList<Products> = ArrayList()
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

    override fun onResume() {
        super.onResume()
        getProductList()
    }
}