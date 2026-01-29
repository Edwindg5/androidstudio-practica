// features/jsonplaceholder/domain/usecases/DeletePostUseCase.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases

import com.edwindiaz.myapplication.features.jsonplaceholder.domain.repositories.PostRepository

class DeletePostUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(id: Int): Result<Unit> {
        return try {
            repository.deletePost(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}