package com.example.restaurant_search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant_search.adapter.RestaurantAdapter
import com.example.restaurant_search.databinding.ActivityMainBinding
import com.example.restaurant_search.models.MenuJson
import com.example.restaurant_search.models.RestaurantJson
import com.example.restaurant_search.utils.AppConstants
import com.example.restaurant_search.utils.SearchUtils
import com.example.restaurant_search.utils.Utils

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val menuMap: HashMap<Int?, MenuJson.Menu?> by lazy { HashMap() }
    lateinit var restaurantList: ArrayList<RestaurantJson.Restaurant>
    val displaySet: HashSet<RestaurantJson.Restaurant?> by lazy { HashSet() }
    lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }


    fun init() {

        setListener()

        Utils.getObjFromJson(
            context = baseContext,
            AppConstants.RESTAURANT_JSON,
            RestaurantJson::class
        )?.let {

            setRecyclerview(it)

        }
    }

    fun setRecyclerview(restaurantJson: RestaurantJson) {

        restaurantJson.restaurants?.let { restaurantList ->

            this.restaurantList = restaurantList
            displaySet.addAll(restaurantList)
            SearchUtils.createMenuMap(baseContext, menuMap, restaurantList)
            binding.rvRestaurant.layoutManager = LinearLayoutManager(this)
            RestaurantAdapter(displaySet, menuMap).let {
                restaurantAdapter = it
                binding.rvRestaurant.adapter = restaurantAdapter
            }

        }
    }

    fun setListener() {

        binding.svRestaurant.setOnClickListener { binding.svRestaurant.onActionViewExpanded() }
        binding.svRestaurant.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                displaySet.clear()

                SearchUtils.searchInRestaurantList(newText, displaySet, restaurantList)
                SearchUtils.searchInMenuMap(newText, menuMap, displaySet, restaurantList)

                restaurantAdapter.notifyDataSetChanged()

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {


                return false
            }

        })
    }


}