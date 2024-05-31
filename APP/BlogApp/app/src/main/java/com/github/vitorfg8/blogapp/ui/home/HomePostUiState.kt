package com.github.vitorfg8.blogapp.ui.home


data class HomeUiState(
    val postList: List<HomePostUiState> = emptyList(),
    val showError: Boolean = false
)

data class HomePostUiState(
    val title: String = "",
    val description: String = "",
    val date: String = ""
)

