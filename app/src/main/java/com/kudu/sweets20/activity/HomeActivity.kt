package com.kudu.sweets20.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudu.sweets20.adapter.CategoriesListViewAdapter
import com.kudu.sweets20.adapter.PopularFoodListViewAdapter
import com.kudu.sweets20.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tempList = ArrayList<String>()

        tempList.add("Fruit Cake")
        tempList.add("Donuts")
        tempList.add("French Desserts")
        tempList.add("Category 4")
        tempList.add("Category 5")
        tempList.add("Category 6")
        tempList.add("Category 7")
        tempList.add("Category 8")
        tempList.add("Category 9")
        tempList.add("Category 10")

        binding.rvCategories.setHasFixedSize(true)
        binding.rvCategories.setItemViewCacheSize(20)
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = CategoriesListViewAdapter(this, tempList)


        val popularList = ArrayList<String>()

        popularList.add("Popular Food 1")
        popularList.add("Popular Food 2")
        popularList.add("Popular Food 3")
        popularList.add("Popular Food 4")
        popularList.add("Popular Food 5")
        popularList.add("Popular Food 6")
        popularList.add("Popular Food 7")
        popularList.add("Popular Food 8")
        popularList.add("Popular Food 9")
        popularList.add("Popular Food 10")

        binding.rvPopulars.setHasFixedSize(true)
        binding.rvPopulars.setItemViewCacheSize(20)
        binding.rvPopulars.layoutManager = LinearLayoutManager(this)
        binding.rvPopulars.adapter = PopularFoodListViewAdapter(this, popularList)


        binding.btnOrderNow.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}