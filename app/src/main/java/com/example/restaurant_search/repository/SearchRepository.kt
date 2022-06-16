package com.example.restaurant_search.repository


import com.example.restaurant_search.models.MenuJson
import com.example.restaurant_search.models.RestaurantJson
import javax.inject.Inject

class SearchRepository @Inject constructor() {


    fun searchInRestaurantList(searchedText:String, displaySet: HashSet<RestaurantJson.Restaurant?>, restaurantList:ArrayList<RestaurantJson.Restaurant> ){

        restaurantList.forEach { restaurant ->

            if (restaurant.name?.contains(searchedText, ignoreCase = true) == true ||
                restaurant.cuisineType?.contains(searchedText, ignoreCase = true) == true ||
                restaurant.address?.contains(searchedText, ignoreCase = true) == true ||
                restaurant.neighborhood?.contains(searchedText, ignoreCase = true) == true
            ) {

                displaySet.add(restaurant)
            }

            restaurant.reviews?.forEach { review ->

               if( review?.comments?.contains(searchedText, ignoreCase = true) == true ||
                   review?.name?.contains(searchedText, ignoreCase = true) == true
               ){
                   displaySet.add(restaurant)
               }

            }
        }

    }

    fun searchInMenuMap(searchedText:String, menuMap: HashMap<Int?, MenuJson.Menu?>, displaySet: HashSet<RestaurantJson.Restaurant?>, restaurantList:ArrayList<RestaurantJson.Restaurant>){

        menuMap.forEach { (_, value) ->

            value?.categories?.forEach { category ->

                if(category?.name?.contains(searchedText, ignoreCase = true) == true){
                    displaySet.add(restaurantList.find { it.id == value.restaurantId })
                }
                category?.menuItems?.forEach { menuItem ->

                    if (menuItem?.name?.contains(searchedText, ignoreCase = true) == true ||
                        menuItem?.description?.contains(searchedText, ignoreCase = true) == true ||
                        menuItem?.price?.contains(searchedText, ignoreCase = true) == true) {
                        displaySet.add(restaurantList.find { it.id == value.restaurantId })
                    }

                }

            }
        }
    }

}