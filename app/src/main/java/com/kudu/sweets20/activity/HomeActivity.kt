package com.kudu.sweets20.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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
    private lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpDrawerToggle()
        setUpActionBar()

        //side nav
        binding.sideNav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.side_nav_addresses -> {
                    Toast.makeText(this, "Address Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.side_nav_favourites -> {
                    Toast.makeText(this, "Favourites Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.side_nav_logout -> {
                    Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.side_nav_orders -> {
                    Toast.makeText(this, "Orders Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.side_nav_profile -> {
                    Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
                R.id.side_nav_voucher -> {
                    Toast.makeText(this, "Voucher Clicked", Toast.LENGTH_SHORT).show()
                }
            }
            return@setNavigationItemSelectedListener true
        }

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
        binding.rvPopulars.adapter?.notifyDataSetChanged()

        //order now button
        binding.btnOrderNow.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        //profile button
        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    //setting up actionbar with back button
    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarHomeActivity)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeButtonEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.menu)

        }
        /* binding.toolbarHomeActivity.setNavigationOnClickListener {
             toggle = ActionBarDrawerToggle(this, binding.root, R.string.open, R.string.close)
             binding.root.addDrawerListener(toggle)
             toggle.syncState()
         }*/
    }

    //setup toggle
    private fun setUpDrawerToggle() {
        toggle = ActionBarDrawerToggle(this, binding.root, R.string.open, R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
//        toggle.isDrawerIndicatorEnabled = true
//        toggle.setHomeAsUpIndicator(R.drawable.menu)
    }

    private fun getProductList() {
        mFireStore.collection(Constants.PRODUCTS)
            .orderBy("title", Query.Direction.DESCENDING)
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
        if (productList != null) {
            productList.clear()
            getProductList()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }
}