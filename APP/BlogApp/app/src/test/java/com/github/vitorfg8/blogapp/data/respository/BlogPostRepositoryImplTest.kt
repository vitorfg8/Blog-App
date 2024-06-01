package com.github.vitorfg8.blogapp.data.respository

import com.github.vitorfg8.blogapp.data.api.BlogPostsApi
import com.github.vitorfg8.blogapp.data.mapper.toDomain
import com.github.vitorfg8.blogapp.data.mapper.toDto
import com.github.vitorfg8.blogapp.data.model.BlogPostDto
import com.github.vitorfg8.blogapp.domain.model.BlogPost
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BlogPostRepositoryImplTest {

    private lateinit var blogPostsApi: BlogPostsApi
    private lateinit var blogPostRepository: BlogPostRepositoryImpl

    @Before
    fun setUp() {
        blogPostsApi = mockk()
        blogPostRepository = BlogPostRepositoryImpl(blogPostsApi)
    }

    @Test
    fun `getBlogPosts returns list of blog posts`() = runBlocking {
        // Given
        val blogPostDtos = listOf(
            BlogPostDto("Title1", "Description1", "2024-06-01T12:36:38.110136Z"),
            BlogPostDto("Title2", "Description2", "2024-06-01T12:36:38.110136Z")
        )
        coEvery { blogPostsApi.getBlogPosts() } returns blogPostDtos

        // When
        val result = blogPostRepository.getBlogPosts().first()

        // Then
        val expected = blogPostDtos.map { it.toDomain() }
        assertEquals(expected, result)
    }

    @Test
    fun `saveBlogPost calls api with correct data`() = runBlocking {
        // Given
        val blogPost = BlogPost("Title1", "Description1", "2024-06-01T12:36:38.110136Z")
        val blogPostDto = blogPost.toDto()
        coEvery { blogPostsApi.saveBlogPost(blogPostDto) } returns Unit

        // When
        blogPostRepository.saveBlogPost(blogPost)

        // Then
        coVerify { blogPostsApi.saveBlogPost(blogPostDto) }
    }
}
