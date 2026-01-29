// features/jsonplaceholder/di/JsonPlaceholderModule.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.di

import com.edwindiaz.myapplication.core.di.AppContainer
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases.DeletePostUseCase
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases.GetPostsUseCase
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.usecases.UpdatePostUseCase
import com.edwindiaz.myapplication.features.jsonplaceholder.presentation.viewmodels.PostsViewModelFactory

class JsonPlaceholderModule(
    private val appContainer: AppContainer
) {

    private fun provideGetPostsUseCase(): GetPostsUseCase {
        return GetPostsUseCase(appContainer.postRepository)
    }

    private fun provideUpdatePostUseCase(): UpdatePostUseCase {
        return UpdatePostUseCase(appContainer.postRepository)
    }

    private fun provideDeletePostUseCase(): DeletePostUseCase {
        return DeletePostUseCase(appContainer.postRepository)
    }

    fun providePostsViewModelFactory(): PostsViewModelFactory {
        return PostsViewModelFactory(
            getPostsUseCase = provideGetPostsUseCase(),
            updatePostUseCase = provideUpdatePostUseCase(),
            deletePostUseCase = provideDeletePostUseCase()
        )
    }
}