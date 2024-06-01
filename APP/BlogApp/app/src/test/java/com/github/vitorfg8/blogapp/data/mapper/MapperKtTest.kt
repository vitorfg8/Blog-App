package com.github.vitorfg8.blogapp.data.mapper

import com.github.vitorfg8.blogapp.data.model.BlogPostDto
import com.github.vitorfg8.blogapp.domain.model.BlogPost
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun `toDomain converts BlogPostDto to BlogPost correctly`() {
        // Given
        val dto = BlogPostDto(
            title = "Sample Title",
            description = "Sample Description",
            date = "2024-05-31T12:36:38.110136Z"
        )

        // When
        val domain = dto.toDomain()

        // Then
        assertEquals("Sample Title", domain.title)
        assertEquals("Sample Description", domain.description)
        assertEquals("2024-05-31T12:36:38.110136Z", domain.date)
    }

    @Test
    fun `toDto converts BlogPost to BlogPostDto correctly`() {
        // Given
        val domain = BlogPost(
            title = "Sample Title",
            description = "Sample Description",
            date = "2024-05-31T12:36:38.110136Z"
        )

        // When
        val dto = domain.toDto()

        // Then
        assertEquals("Sample Title", dto.title)
        assertEquals("Sample Description", dto.description)
        assertEquals("2024-05-31T12:36:38.110136Z", dto.date)
    }
}
