package com.edwindiaz.myapplication.core.network

import com.edwindiaz.myapplication.features.jsonplaceholder.data.repositories.PostRepositoryImpl
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.repositories.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository
}