// features/jsonplaceholder/domain/usecases/UpdatePostUseCase.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.entities.Post
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.repositories.PostRepository
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(post: Post): Result<Post> = try {
        Result.success(repository.updatePost(post))
    } catch (e: Exception) {
        Result.failure(e)
    }
}