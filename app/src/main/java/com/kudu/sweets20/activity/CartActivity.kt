package com.kudu.sweets20.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kudu.sweets20.R
import com.kudu.sweets20.databinding.ActivityCartBinding
import com.kudu.sweets20.fragment.CartItemsFragment
import com.kudu.sweets20.fragment.ConfirmOrderFragment
import com.kudu.sweets20.fragment.ReviewPaymentFragment

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()

        loadFragment(CartItemsFragment())

        //nav bar cart
        binding.cartNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_1 -> {
                    loadFragment(CartItemsFragment())
                }
                R.id.nav_2 -> {
                    loadFragment(ReviewPaymentFragment())
                }
                R.id.nav_3 -> {
                    loadFragment(ConfirmOrderFragment())
                }
            }
            return@setOnItemSelectedListener true
        }

    }

    //load fragmment
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.cart_fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    //setting up actionbar with back button
    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarCartActivity)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.back)
        }
        binding.toolbarCartActivity.setNavigationOnClickListener { onBackPressed() }
    }
}