package com.example.restaurant_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant_search.databinding.RowRestaurantBinding
import com.example.restaurant_search.models.MenuJson
import com.example.restaurant_search.models.RestaurantJson
import com.example.restaurant_search.utils.Utils

class RestaurantAdapter(
    var restuarantList: HashSet<RestaurantJson.Restaurant?>,
    var menuMap: HashMap<Int?, MenuJson.Menu?>
) : RecyclerView.Adapter<RestaurantAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantAdapter.MyViewHolder {
        val binding =
            RowRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RestaurantAdapter.MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantAdapter.MyViewHolder, position: Int) {

        restuarantList.elementAt(position)?.let { restaurant ->

            holder.bind(restaurant)

            holder.binding.tvMenu.text = Utils.getMenuString(getCategoryList(restaurant.id))
        }


    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return restuarantList.size
    }

    class MyViewHolder(val binding: RowRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: RestaurantJson.Restaurant) {

            binding.tvRestuarantName.text = restaurant.name
            binding.tvCuisineType.text = restaurant.cuisineType
        }


    }

    fun getCategoryList(restId: Int?): ArrayList<MenuJson.Menu.Category?>? {

        return menuMap.get(restId)?.categories
    }

}