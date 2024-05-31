package com.github.vitorfg8.blogapp.data.api

import com.github.vitorfg8.blogapp.data.model.BlogPostDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BlogPostsApi {
    @GET("/blogposts")
    suspend fun getBlogPosts(): List<BlogPostDto>

    @POST("/blogposts")
    suspend fun saveBlogPost(@Body blogPostDto: BlogPostDto)
}