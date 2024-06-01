package com.github.vitorfg8.blogapp.domain.usecase

import com.github.vitorfg8.blogapp.domain.model.BlogPost
import com.github.vitorfg8.blogapp.domain.repository.BlogPostRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SavePostUseCaseTest {

    @Test
    fun `invoke saves blog post in repository`() = runBlocking {
        // Given
        val mockRepository = mockk<BlogPostRepository>(relaxed = true)
        val blogPost = BlogPost(
            title = "Title",
            description = "Description",
            date = "2024-05-31T12:36:38.110136Z"
        )

        val useCase = SavePostUseCase(mockRepository)

        // When
        useCase.invoke(blogPost)

        // Then
        coVerify { mockRepository.saveBlogPost(blogPost) }
    }
}