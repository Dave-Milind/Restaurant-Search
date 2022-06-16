package com.example.restaurant_search.di

import com.example.restaurant_search.service.SomeService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {


    // if we have api call in future, this is how we will get retrofit dependency from this module
    @Provides
    @Singleton
    fun getBaseUrl():String= "Some base url"

    @Provides
    @Singleton
    fun getRetrofit(baseUrl:String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    fun getPlaceHolderService(retrofit: Retrofit) : SomeService {

        return retrofit.create(SomeService::class.java)
    }


}