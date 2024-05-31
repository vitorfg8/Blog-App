package com.github.vitorfg8.blogapp.data.mapper

import com.github.vitorfg8.blogapp.data.model.BlogPostDto
import com.github.vitorfg8.blogapp.domain.model.BlogPost

fun BlogPostDto.toDomain(): BlogPost {
    return BlogPost(
        title = title,
        description = description,
        date = date
    )
}

fun BlogPost.toDto(): BlogPostDto {
    return BlogPostDto(
        title = title,
        description = description,
        date = date
    )
}