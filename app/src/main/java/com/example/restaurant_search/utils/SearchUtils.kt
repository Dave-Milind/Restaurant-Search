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


}