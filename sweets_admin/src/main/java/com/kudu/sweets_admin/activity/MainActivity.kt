package com.kudu.sweets_admin.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kudu.sweets_admin.R
import com.kudu.sweets_admin.databinding.ActivityMainBinding
import com.kudu.sweets_admin.fragment.ProductsFragment
import com.kudu.sweets_admin.fragment.SoldFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(ProductsFragment())

        //bottom nav
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_products -> {
                    loadFragment(ProductsFragment())
                }
                R.id.nav_sold -> {
                    loadFragment(SoldFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}