// features/jsonplaceholder/domain/usecases/UpdatePostUseCase.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases

import com.edwindiaz.myapplication.features.jsonplaceholder.domain.entities.Post
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.repositories.PostRepository

class UpdatePostUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(post: Post): Result<Post> {
        return try {
            val updatedPost = repository.updatePost(post)
            Result.success(updatedPost)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}