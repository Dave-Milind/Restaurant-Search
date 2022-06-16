package com.example.restaurant_search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant_search.adapter.RestaurantAdapter
import com.example.restaurant_search.databinding.ActivityMainBinding
import com.example.restaurant_search.models.RestaurantJson
import com.example.restaurant_search.utils.AppConstants
import com.example.restaurant_search.utils.Utils
import com.example.restaurant_search.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val mainViewModel: MainViewModel by viewModels()
    lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setListener()
    }

    private fun init() {
        Utils.getObjFromJson(
            baseContext,
            AppConstants.RESTAURANT_JSON,
            RestaurantJson::class)?.let {
            setRecyclerView(it)
        }
    }

    private fun setRecyclerView(restaurantJson: RestaurantJson) {

        restaurantJson.restaurants?.let { restaurantList ->
            mainViewModel.setData(baseContext, restaurantList)
            binding.rvRestaurant.layoutManager = LinearLayoutManager(this)
            RestaurantAdapter(mainViewModel.displaySet, mainViewModel.menuMap).let {
                restaurantAdapter = it
                binding.rvRestaurant.adapter = restaurantAdapter
            }

        }
    }

    private fun setListener() {

        binding.svRestaurant.setOnClickListener { binding.svRestaurant.onActionViewExpanded() }
        binding.svRestaurant.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                mainViewModel.displaySet.clear()
                mainViewModel.searchInRestaurantList(newText)
                mainViewModel.searchInMenuList(newText)
                restaurantAdapter.notifyDataSetChanged()

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })
    }


}