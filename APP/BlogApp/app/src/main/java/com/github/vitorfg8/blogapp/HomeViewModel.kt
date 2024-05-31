package com.github.vitorfg8.blogapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorfg8.blogapp.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class HomeViewModel(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    fun getPosts() {
        viewModelScope.launch {
            getPostsUseCase().collect { list ->
                _uiState.value.blogPostList = list.map {
                    HomeBlogPost(
                        title = it.title,
                        description = it.description,
                        date = formatDateTime(it.date),
                    )
                }
            }
        }
    }
}

private fun formatDateTime(dateTimeStr: String): String {
    val zonedDateTime = ZonedDateTime.parse(dateTimeStr)
    val localDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm")
    return localDateTime.format(formatter)
}