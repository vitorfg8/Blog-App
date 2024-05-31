package com.github.vitorfg8.blogapp.domain.usecase

import com.github.vitorfg8.blogapp.domain.model.BlogPost
import com.github.vitorfg8.blogapp.domain.repository.BlogPostRepository
import kotlinx.coroutines.flow.Flow

class GetPostsUseCase(private val blogPostRepository: BlogPostRepository) {
    suspend operator fun invoke(): Flow<List<BlogPost>> {
        return blogPostRepository.getBlogPosts()
    }
}