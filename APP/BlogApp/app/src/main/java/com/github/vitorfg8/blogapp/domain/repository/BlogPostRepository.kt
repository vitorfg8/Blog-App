package com.github.vitorfg8.blogapp.domain.repository

import com.github.vitorfg8.blogapp.domain.model.BlogPost
import kotlinx.coroutines.flow.Flow

interface BlogPostRepository {
    suspend fun getBlogPosts(): Flow<List<BlogPost>>
    suspend fun saveBlogPost(blogPost: BlogPost)
}