// features/jsonplaceholder/domain/usecases/GetPostsUseCase.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases

import com.edwindiaz.myapplication.features.jsonplaceholder.domain.entities.Post
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.repositories.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): Result<List<Post>> = try {
        Result.success(repository.getPosts())
    } catch (e: Exception) {
        Result.failure(e)
    }
}