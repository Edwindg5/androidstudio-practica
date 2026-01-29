// core/network/JsonPlaceholderApi.kt
package com.edwindiaz.myapplication.core.network

import com.edwindiaz.myapplication.features.jsonplaceholder.data.datasources.remote.model.PostDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Body

interface JsonPlaceholderApi {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): PostDto

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: PostDto): PostDto

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int)
}