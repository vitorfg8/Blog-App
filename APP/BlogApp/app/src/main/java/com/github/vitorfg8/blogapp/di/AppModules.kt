package com.github.vitorfg8.blogapp.di

import com.github.vitorfg8.blogapp.AddPostViewModel
import com.github.vitorfg8.blogapp.data.respository.BlogPostRepositoryImpl
import com.github.vitorfg8.blogapp.domain.usecase.GetCurrentFormattedTimeUseCase
import com.github.vitorfg8.blogapp.domain.usecase.SavePostUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        AddPostViewModel(
            savePostUseCase = SavePostUseCase(
                blogPostRepository = BlogPostRepositoryImpl(
                    blogPostsApi = get()
                )
            ), getCurrentFormattedTimeUseCase = GetCurrentFormattedTimeUseCase()
        )
    }
}