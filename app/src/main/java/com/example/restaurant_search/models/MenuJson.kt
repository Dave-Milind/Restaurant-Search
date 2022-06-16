package com.example.restaurant_search.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MenuJson(
    @SerializedName("menus")
    val menus: List<Menu?>? = null
) {
    @Keep
    data class Menu(
        @SerializedName("categories")
        val categories: ArrayList<Category?>? = null,
        @SerializedName("restaurantId")
        val restaurantId: Int? = null
    ) {
        @Keep
        data class Category(
            @SerializedName("id")
            val id: String? = null,
            @SerializedName("menu-items")
            val menuItems: List<MenuItem?>? = null,
            @SerializedName("name")
            val name: String? = null
        ) {
            @Keep
            data class MenuItem(
                @SerializedName("description")
                val description: String? = null,
                @SerializedName("id")
                val id: String? = null,
                @SerializedName("images")
                val images: List<Any?>? = null,
                @SerializedName("name")
                val name: String? = null,
                @SerializedName("price")
                val price: String? = null
            )
        }
    }
}