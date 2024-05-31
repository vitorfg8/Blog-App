package com.github.vitorfg8.blogapp.domain.usecase

import com.github.vitorfg8.blogapp.domain.model.BlogPost
import com.github.vitorfg8.blogapp.domain.repository.BlogPostRepository

class SavePostUseCase(private val blogPostRepository: BlogPostRepository) {
    suspend operator fun invoke(blogPost: BlogPost) {
        blogPostRepository.saveBlogPost(blogPost)
    }
}