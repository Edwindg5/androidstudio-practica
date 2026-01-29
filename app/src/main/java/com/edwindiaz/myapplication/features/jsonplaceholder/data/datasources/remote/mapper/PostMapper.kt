// features/jsonplaceholder/data/datasources/remote/mapper/PostMapper.kt
package com.edwindiaz.myapplication.features.jsonplaceholder.data.datasources.remote.mapper

import com.edwindiaz.myapplication.features.jsonplaceholder.data.datasources.remote.model.PostDto
import com.edwindiaz.myapplication.features.jsonplaceholder.domain.entities.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body
    )
}

fun Post.toDto(): PostDto {
    return PostDto(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body
    )
}