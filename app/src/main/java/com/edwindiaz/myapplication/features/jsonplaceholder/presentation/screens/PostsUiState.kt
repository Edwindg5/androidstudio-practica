// features/jsonplaceholder/presentation/screens/uiState/PostsUiState.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.presentation.screens

import com.edwindiaz.myapplication.features.jsonplaceholder.domain.entities.Post

data class PostsUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String? = null,
    val selectedPost: Post? = null,
    val isUpdating: Boolean = false,
    val isDeleting: Boolean = false
)