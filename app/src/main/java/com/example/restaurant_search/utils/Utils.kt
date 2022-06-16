package com.example.restaurant_search.utils

import android.content.Context
import com.example.restaurant_search.models.MenuJson
import com.example.restaurant_search.models.RestaurantJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import kotlin.reflect.KClass

object Utils {


    fun <T : Any> getObjFromJson(context: Context, fileName: String, clazz: KClass<T>): T? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        val gson = Gson()


        return when (clazz) {
            RestaurantJson::class -> {
                return gson.fromJson(jsonString, object : TypeToken<RestaurantJson>() {}.type)
            }
            MenuJson::class -> {
                return gson.fromJson(jsonString, object : TypeToken<MenuJson>() {}.type)
            }
            else -> null
        }

    }

    fun getMenuString(categoryList: ArrayList<MenuJson.Menu.Category?>?): StringBuilder {

        val builder = StringBuilder()
        categoryList?.forEach { category ->

            category.let {

                it?.menuItems?.let { menuItemList ->

                    for (menuItem in menuItemList) {
                        builder.append(menuItem?.name + "\n")
                    }


                }

            }

        }
        return builder
    }
}