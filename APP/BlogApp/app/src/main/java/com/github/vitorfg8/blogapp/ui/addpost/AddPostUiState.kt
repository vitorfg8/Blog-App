package com.github.vitorfg8.blogapp.ui.addpost

data class AddPostUiState(
    val postTitle: String = "",
    val postDescription: String = "",
    val isButtonEnabled: Boolean = false,
    val showError: Boolean = false,
    val isPostSaved: Boolean = false
)
