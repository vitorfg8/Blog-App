package com.github.vitorfg8.blogapp.domain.usecase

import com.github.vitorfg8.blogapp.domain.model.BlogPost
import com.github.vitorfg8.blogapp.domain.repository.BlogPostRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPostsUseCaseTest {
    @Test
    fun `invoke returns blog posts from repository`() = runBlocking {
        // Given
        val mockRepository = mockk<BlogPostRepository>()
        val expectedPosts = listOf(
            BlogPost(
                title = "Title 1",
                description = "Description 1",
                date = "2024-05-31T12:36:38.110136Z"
            ),
            BlogPost(
                title = "Title 2",
                description = "Description 2",
                date = "2024-05-31T12:36:38.110136Z"
            )
        )
        coEvery { mockRepository.getBlogPosts() } returns flowOf(expectedPosts)

        val useCase = GetPostsUseCase(mockRepository)

        // When
        val result: Flow<List<BlogPost>> = useCase.invoke()

        // Then
        result.collect { posts ->
            assertEquals(expectedPosts, posts)
        }
    }
}