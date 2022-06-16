package com.example.restaurant_search.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RestaurantJson(
    @SerializedName("restaurants")
    val restaurants: ArrayList<Restaurant>? = null
) {
    @Keep
    data class Restaurant(
        @SerializedName("address")
        val address: String? = null,
        @SerializedName("cuisine_type")
        val cuisineType: String? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("latlng")
        val latlng: Latlng? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("neighborhood")
        val neighborhood: String? = null,
        @SerializedName("operating_hours")
        val operatingHours: OperatingHours? = null,
        @SerializedName("photograph")
        val photograph: String? = null,
        @SerializedName("reviews")
        val reviews: List<Review?>? = null
    ) {
        @Keep
        data class Latlng(
            @SerializedName("lat")
            val lat: Double? = null,
            @SerializedName("lng")
            val lng: Double? = null
        )

        @Keep
        data class OperatingHours(
            @SerializedName("Friday")
            val friday: String? = null,
            @SerializedName("Monday")
            val monday: String? = null,
            @SerializedName("Saturday")
            val saturday: String? = null,
            @SerializedName("Sunday")
            val sunday: String? = null,
            @SerializedName("Thursday")
            val thursday: String? = null,
            @SerializedName("Tuesday")
            val tuesday: String? = null,
            @SerializedName("Wednesday")
            val wednesday: String? = null
        )

        @Keep
        data class Review(
            @SerializedName("comments")
            val comments: String? = null,
            @SerializedName("date")
            val date: String? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("rating")
            val rating: Int? = null
        )
    }
}