package com.github.vitorfg8.blogapp.data.model

import com.google.gson.annotations.SerializedName

data class BlogPostDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("date")
    val date: String
)