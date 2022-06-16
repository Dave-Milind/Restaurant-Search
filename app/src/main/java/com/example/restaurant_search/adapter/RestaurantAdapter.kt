package com.example.restaurant_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant_search.databinding.RowRestaurantBinding
import com.example.restaurant_search.models.MenuJson
import com.example.restaurant_search.models.RestaurantJson

class RestaurantAdapter(
    private var restaurantsList: HashSet<RestaurantJson.Restaurant?>,
    var menuMap: HashMap<Int?, MenuJson.Menu?>
) : RecyclerView.Adapter<RestaurantAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            RowRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        restaurantsList.elementAt(position)?.let { restaurant ->
            holder.bind(restaurant)
        }

    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return restaurantsList.size
    }

    class MyViewHolder(val binding: RowRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: RestaurantJson.Restaurant) {

            binding.tvRestuarantName.text = restaurant.name
            binding.tvCuisineType.text = restaurant.cuisineType
            binding.tvNeighborhood.text = restaurant.neighborhood
        }


    }

    fun getCategoryList(restId: Int?): ArrayList<MenuJson.Menu.Category?>? {

        return menuMap.get(restId)?.categories
    }

}