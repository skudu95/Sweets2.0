package com.kudu.sweets_admin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kudu.sweets_admin.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}