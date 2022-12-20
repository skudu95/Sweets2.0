package com.kudu.sweets20.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudu.common.model.Categories
import com.kudu.sweets20.R
import com.kudu.sweets20.adapter.CategoriesListViewAdapter
import com.kudu.sweets20.adapter.PopularFoodListViewAdapter
import com.kudu.sweets20.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

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


        binding.rvCategories.setHasFixedSize(true)
        binding.rvCategories.setItemViewCacheSize(20)
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = CategoriesListViewAdapter(this, categoryList)


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