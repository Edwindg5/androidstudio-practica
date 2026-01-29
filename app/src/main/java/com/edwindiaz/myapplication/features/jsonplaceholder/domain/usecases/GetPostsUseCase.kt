// features/jsonplaceholder/domain/usecases/GetPostsUseCase.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases

import com.edwindiaz.myapplication.features.jsonplaceholder.domain.entities.Post
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.repositories.PostRepository

class GetPostsUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): Result<List<Post>> {
        return try {
            val posts = repository.getPosts()
            Result.success(posts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}