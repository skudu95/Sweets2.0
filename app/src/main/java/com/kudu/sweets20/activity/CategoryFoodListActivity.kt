package com.kudu.sweets20.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.kudu.common.model.Products
import com.kudu.common.util.Constants
import com.kudu.sweets20.R
import com.kudu.sweets20.adapter.CategoryFoodListViewAdapter
import com.kudu.sweets20.databinding.ActivityCategoryFoodListBinding

class CategoryFoodListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryFoodListBinding

    private var mCategoryTitle: String = ""
    private val productList: ArrayList<Products> = ArrayList()
    private val mFireStore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryFoodListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()

        if (intent.hasExtra(Constants.CATEGORY_TITLE)) {
            mCategoryTitle = intent.getStringExtra(Constants.CATEGORY_TITLE)!!
            binding.tvTitle.text = mCategoryTitle
        }

        getProductList(mCategoryTitle)

        //recyclerview
        binding.rvProductListCategoryFoodList.setHasFixedSize(true)
        binding.rvProductListCategoryFoodList.setItemViewCacheSize(20)
        binding.rvProductListCategoryFoodList.layoutManager = LinearLayoutManager(this)
        binding.rvProductListCategoryFoodList.adapter =
            CategoryFoodListViewAdapter(this, productList)
        binding.rvProductListCategoryFoodList.adapter?.notifyDataSetChanged()

    }

    //category product details list
    private fun getProductList(categoryTitle: String) {
        mFireStore.collection(Constants.PRODUCTS)
            .whereEqualTo("category", categoryTitle)
            .get()
            .addOnSuccessListener { document ->
                Log.e("CategoryProdList", document.documents.toString())

//                val productList: ArrayList<Products> = ArrayList()
                for (i in document.documents) {
                    val product = i.toObject(Products::class.java)
                    product!!.id = i.id
                    productList.add(product)
                }
            }
            .addOnFailureListener { e ->
                Log.e("CategoryProdListError", "Error while fetching product list", e)
            }
    }

    //setting up actionbar with back button
    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarCategoryFoodListActivity)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.back)
        }
        binding.toolbarCategoryFoodListActivity.setNavigationOnClickListener { onBackPressed() }
    }
}