package com.github.vitorfg8.blogapp.ui.home

data class HomeUiState(
    var blogPostList: List<HomeBlogPost> = emptyList()
)

data class HomeBlogPost(
    val title: String,
    val description: String,
    val date: String
)

