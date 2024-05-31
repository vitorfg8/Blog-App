package com.github.vitorfg8.blogapp

data class AddPostUiState(
    val postTitle: String = "",
    val postDescription: String = "",
    val isButtonEnabled: Boolean = false,
)
