// features/jsonplaceholder/presentation/viewmodels/PostsViewModelFactory.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases.DeletePostUseCase
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases.GetPostsUseCase
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases.UpdatePostUseCase

class PostsViewModelFactory(
    private val getPostsUseCase: GetPostsUseCase,
    private val updatePostUseCase: UpdatePostUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            return PostsViewModel(
                getPostsUseCase,
                updatePostUseCase,
                deletePostUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}