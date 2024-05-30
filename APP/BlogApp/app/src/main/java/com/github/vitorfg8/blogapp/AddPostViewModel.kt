package com.github.vitorfg8.blogapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddPostViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AddPostUiState())
    val uiState = _uiState.asStateFlow()


    fun updateTitle(newTitle: String) {
        _uiState.update {
            it.copy(
                postTitle = newTitle,
                isButtonEnabled = newTitle.isNotBlank() && uiState.value.postDescription.isNotBlank()
            )
        }
    }

    fun updateDescription(newDescription: String) {
        _uiState.update {
            it.copy(
                postDescription = newDescription,
                isButtonEnabled = newDescription.isNotEmpty() && uiState.value.postTitle.isNotBlank()
            )
        }
    }

    fun savePost() {
        if (uiState.value.isButtonEnabled) {
            //TODO
        }
    }
}