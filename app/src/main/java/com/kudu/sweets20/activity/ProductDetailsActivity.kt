package com.kudu.sweets20.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.kudu.common.model.Products
import com.kudu.common.util.Constants
import com.kudu.common.util.GlideLoader
import com.kudu.sweets20.R
import com.kudu.sweets20.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    private var mProductId: String = ""
    private lateinit var mProductDetails: Products
    private val mFireStore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()

        if (intent.hasExtra(Constants.EXTRA_PRODUCT_ID)) {
            mProductId = intent.getStringExtra(Constants.EXTRA_PRODUCT_ID)!!
        }

        getProductDetails(mProductId)

        binding.btnCartPDA.setOnClickListener {
            Toast.makeText(this, "Cart Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    //product details
    private fun getProductDetails(productId: String) {
        mFireStore.collection(Constants.PRODUCTS)
            .document(productId)
            .get()
            .addOnSuccessListener { document ->
                Log.e("ProdDetails", document.toString())
                val product = document.toObject(Products::class.java)
                if (product != null) {
                    mProductDetails = product
                    GlideLoader(this).loadProductPicture(product.image, binding.productImage)
                    binding.productTitlePDA.text = product.title
                    binding.productDetailsPDA.text = product.description
                    binding.tvProductPricePDA.text = buildString {
                        append("$ ")
                        append(product.price)
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("ProdDetailsError", "Error while getting product details", e)
            }
    }

    //setting up actionbar with back button
    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarProductDetailsActivity)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.back)
        }
        binding.toolbarProductDetailsActivity.setNavigationOnClickListener { onBackPressed() }
    }
}