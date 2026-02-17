// features/jsonplaceholder/presentation/viewmodels/PostsViewModel.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.entities.Post
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases.DeletePostUseCase
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases.GetPostsUseCase
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases.UpdatePostUseCase
import com.edwindiaz.myapplication.features.jsonplaceholder.presentation.screens.PostsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val updatePostUseCase: UpdatePostUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            val result = getPostsUseCase()
            _uiState.update { current ->
                result.fold(
                    onSuccess = { posts -> current.copy(isLoading = false, posts = posts) },
                    onFailure = { error -> current.copy(isLoading = false, error = error.message) }
                )
            }
        }
    }

    fun updatePost(post: Post) {
        _uiState.update { it.copy(isUpdating = true, error = null) }
        viewModelScope.launch {
            val result = updatePostUseCase(post)
            _uiState.update { current ->
                result.fold(
                    onSuccess = { updated ->
                        current.copy(
                            isUpdating = false,
                            posts = current.posts.map { if (it.id == updated.id) updated else it },
                            selectedPost = updated
                        )
                    },
                    onFailure = { error -> current.copy(isUpdating = false, error = error.message) }
                )
            }
        }
    }

    fun deletePost(id: Int) {
        _uiState.update { it.copy(isDeleting = true, error = null) }
        viewModelScope.launch {
            val result = deletePostUseCase(id)
            _uiState.update { current ->
                result.fold(
                    onSuccess = {
                        current.copy(
                            isDeleting = false,
                            posts = current.posts.filter { it.id != id },
                            selectedPost = null
                        )
                    },
                    onFailure = { error -> current.copy(isDeleting = false, error = error.message) }
                )
            }
        }
    }

    fun selectPost(post: Post?) {
        _uiState.update { it.copy(selectedPost = post) }
    }
}