// features/jsonplaceholder/domain/repositories/PostRepository.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.domain.repositories

import com.edwindiaz.myapplication.features.jsonplaceholder.domain.entities.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getPost(id: Int): Post
    suspend fun updatePost(post: Post): Post
    suspend fun deletePost(id: Int)
}