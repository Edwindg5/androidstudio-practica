// features/jsonplaceholder/domain/usecases/DeletePostUseCase.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases

import com.edwindiaz.myapplication.features.jsonplaceholder.domain.repositories.PostRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(id: Int): Result<Unit> = try {
        Result.success(repository.deletePost(id))
    } catch (e: Exception) {
        Result.failure(e)
    }
}