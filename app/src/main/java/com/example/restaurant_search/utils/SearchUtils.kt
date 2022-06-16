package com.example.restaurant_search.utils

import android.content.Context
import com.example.restaurant_search.models.MenuJson
import com.example.restaurant_search.models.RestaurantJson

object SearchUtils {


    fun createMenuMap(context: Context, menuMap: HashMap<Int?, MenuJson.Menu?>, restaurantList: List<RestaurantJson.Restaurant>) {

        Utils.getObjFromJson(
            context ,
            AppConstants.MENU_JSON,
            MenuJson::class
        )?.let { menuJson ->

            restaurantList.forEach {

                val menu = menuJson.menus?.find { menu -> menu?.restaurantId == it.id }
                menuMap.put(it.id, menu)
            }
        }


    }


    fun searchInRestaurantList(searchedText:String, displayList: HashSet<RestaurantJson.Restaurant?>,restaurantList:ArrayList<RestaurantJson.Restaurant> ){

        restaurantList.forEach { restaurant ->

            if (restaurant.name?.contains(searchedText, ignoreCase = true) == true ||
                restaurant.cuisineType?.contains(searchedText, ignoreCase = true) == true ||
                restaurant.address?.contains(searchedText, ignoreCase = true) == true ||
                restaurant.neighborhood?.contains(searchedText, ignoreCase = true) == true
            ) {

                displayList.add(restaurant)
            }
        }

    }

    fun searchInMenuMap(searchedText:String, menuMap: HashMap<Int?, MenuJson.Menu?>, displayList: HashSet<RestaurantJson.Restaurant?>, restaurantList:ArrayList<RestaurantJson.Restaurant>){

        menuMap.forEach { (_, value) ->

            value?.categories?.forEach { category ->
                category?.menuItems?.forEach { menuItem ->

                    if (menuItem?.name?.contains(searchedText, ignoreCase = true) == true ||
                        menuItem?.description?.contains(searchedText, ignoreCase = true) == true ||
                        menuItem?.price?.contains(searchedText, ignoreCase = true) == true) {
                        displayList.add(restaurantList.find { it.id == value.restaurantId })
                    }

                }

            }
        }
    }

}