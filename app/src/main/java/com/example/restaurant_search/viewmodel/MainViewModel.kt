package com.example.restaurant_search.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant_search.models.MenuJson
import com.example.restaurant_search.models.RestaurantJson
import com.example.restaurant_search.repository.SearchRepository
import com.example.restaurant_search.utils.SearchUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var searchRepository: SearchRepository) : ViewModel() {


    val menuMap: HashMap<Int?, MenuJson.Menu?> by lazy { HashMap() }
    lateinit var restaurantList: ArrayList<RestaurantJson.Restaurant>
    val displaySet: HashSet<RestaurantJson.Restaurant?> by lazy { HashSet() }


    fun setData(context: Context, mrestaurantList: ArrayList<RestaurantJson.Restaurant>) {

        restaurantList = mrestaurantList
        displaySet.addAll(restaurantList)
        SearchUtils.createMenuMap(context, menuMap, restaurantList)
    }

    fun searchInRestaurantList(searchedText: String) {

        viewModelScope.launch(Dispatchers.Default) {
            searchRepository.searchInRestaurantList(searchedText, displaySet, restaurantList)
        }

    }

    fun searchInMenuList(searchedText: String) {
        viewModelScope.launch(Dispatchers.Default) {
            searchRepository.searchInMenuMap(searchedText, menuMap, displaySet, restaurantList)
        }
    }

}

