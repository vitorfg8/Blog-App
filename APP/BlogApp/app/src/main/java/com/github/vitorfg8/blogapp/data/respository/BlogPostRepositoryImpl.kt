package com.github.vitorfg8.blogapp.data.respository

import com.github.vitorfg8.blogapp.data.api.BlogPostsApi
import com.github.vitorfg8.blogapp.data.mapper.toDomain
import com.github.vitorfg8.blogapp.data.mapper.toDto
import com.github.vitorfg8.blogapp.domain.model.BlogPost
import com.github.vitorfg8.blogapp.domain.repository.BlogPostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BlogPostRepositoryImpl(
    private val blogPostsApi: BlogPostsApi
) : BlogPostRepository {
    override suspend fun getBlogPosts(): Flow<List<BlogPost>> = flow {
        emit(blogPostsApi.getBlogPosts().map { it.toDomain() })
    }

    override suspend fun saveBlogPost(blogPost: BlogPost) {
        blogPostsApi.saveBlogPost(blogPost.toDto())
    }
}