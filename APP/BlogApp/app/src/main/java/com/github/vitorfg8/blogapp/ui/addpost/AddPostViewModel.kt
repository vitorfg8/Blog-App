package com.github.vitorfg8.blogapp.ui.addpost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorfg8.blogapp.domain.model.BlogPost
import com.github.vitorfg8.blogapp.domain.usecase.GetCurrentFormattedTimeUseCase
import com.github.vitorfg8.blogapp.domain.usecase.SavePostUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddPostViewModel(
    private val savePostUseCase: SavePostUseCase,
    private val getCurrentFormattedTimeUseCase: GetCurrentFormattedTimeUseCase,
) : ViewModel() {
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
            viewModelScope.launch {
                try {
                    savePostUseCase(
                        BlogPost(
                            title = uiState.value.postTitle,
                            description = uiState.value.postDescription,
                            date = getCurrentFormattedTimeUseCase(),
                        )
                    )
                    _uiState.update {
                        it.copy(
                            showError = false,
                            isPostSaved = true
                        )
                    }
                } catch (e: Exception) {
                    _uiState.update { it.copy(showError = true) }
                }
            }
        }
    }

    fun resetErrorState() {
        _uiState.update { it.copy(showError = false) }
    }
}