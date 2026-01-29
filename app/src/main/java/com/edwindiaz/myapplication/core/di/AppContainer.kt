// core/di/AppContainer.kt
package com.edwindiaz.myapplication.core.di

import android.content.Context
import com.edwindiaz.myapplication.core.network.JsonPlaceholderApi
import com.edwindiaz.myapplication.features.jsonplaceholder.data.repositories.PostRepositoryImpl
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.repositories.PostRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val jsonPlaceholderApi: JsonPlaceholderApi by lazy {
        retrofit.create(JsonPlaceholderApi::class.java)
    }

    val postRepository: PostRepository by lazy {
        PostRepositoryImpl(jsonPlaceholderApi)
    }
}