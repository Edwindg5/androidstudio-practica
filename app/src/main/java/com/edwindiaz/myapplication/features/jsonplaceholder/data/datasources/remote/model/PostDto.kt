// features/jsonplaceholder/data/datasources/remote/model/PostDto.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.data.datasources.remote.model

data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)