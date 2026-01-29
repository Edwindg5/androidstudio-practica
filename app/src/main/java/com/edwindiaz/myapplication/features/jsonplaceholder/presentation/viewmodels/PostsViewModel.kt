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

class PostsViewModel(
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
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { posts ->
                        currentState.copy(isLoading = false, posts = posts)
                    },
                    onFailure = { error ->
                        currentState.copy(isLoading = false, error = error.message)
                    }
                )
            }
        }
    }

    fun updatePost(post: Post) {
        _uiState.update { it.copy(isUpdating = true, error = null) }

        viewModelScope.launch {
            val result = updatePostUseCase(post)
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { updatedPost ->
                        val updatedList = currentState.posts.map {
                            if (it.id == updatedPost.id) updatedPost else it
                        }
                        currentState.copy(
                            isUpdating = false,
                            posts = updatedList,
                            selectedPost = updatedPost
                        )
                    },
                    onFailure = { error ->
                        currentState.copy(isUpdating = false, error = error.message)
                    }
                )
            }
        }
    }

    fun deletePost(id: Int) {
        _uiState.update { it.copy(isDeleting = true, error = null) }

        viewModelScope.launch {
            val result = deletePostUseCase(id)
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = {
                        val updatedList = currentState.posts.filter { it.id != id }
                        currentState.copy(
                            isDeleting = false,
                            posts = updatedList,
                            selectedPost = null
                        )
                    },
                    onFailure = { error ->
                        currentState.copy(isDeleting = false, error = error.message)
                    }
                )
            }
        }
    }

    fun selectPost(post: Post?) {
        _uiState.update { it.copy(selectedPost = post) }
    }
}