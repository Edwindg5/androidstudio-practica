// features/jsonplaceholder/data/repositories/PostRepositoryImpl.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.data.repositories

import com.edwindiaz.myapplication.core.network.JsonPlaceholderApi
import com.edwindiaz.myapplication.features.jsonplaceholder.data.datasources.remote.mapper.toDomain
import com.edwindiaz.myapplication.features.jsonplaceholder.data.datasources.remote.mapper.toDto
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.entities.Post
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.repositories.PostRepository

class PostRepositoryImpl(
    private val api: JsonPlaceholderApi
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        return api.getPosts().map { it.toDomain() }
    }

    override suspend fun getPost(id: Int): Post {
        return api.getPost(id).toDomain()
    }

    override suspend fun updatePost(post: Post): Post {
        return api.updatePost(post.id, post.toDto()).toDomain()
    }

    override suspend fun deletePost(id: Int) {
        api.deletePost(id)
    }
}