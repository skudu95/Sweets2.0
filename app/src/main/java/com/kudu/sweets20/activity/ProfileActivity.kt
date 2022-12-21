package com.kudu.sweets20.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kudu.sweets20.R
import com.kudu.sweets20.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()
    }

    //setting up actionbar with back button
    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarProfileActivity)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.back_logo_color)
        }
        binding.toolbarProfileActivity.setNavigationOnClickListener { onBackPressed() }
    }
}