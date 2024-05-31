package com.github.vitorfg8.blogapp.di

import com.github.vitorfg8.blogapp.data.respository.BlogPostRepositoryImpl
import com.github.vitorfg8.blogapp.domain.usecase.GetCurrentFormattedTimeUseCase
import com.github.vitorfg8.blogapp.domain.usecase.GetPostsUseCase
import com.github.vitorfg8.blogapp.domain.usecase.SavePostUseCase
import com.github.vitorfg8.blogapp.ui.addpost.AddPostViewModel
import com.github.vitorfg8.blogapp.ui.home.HomeViewModel
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
    viewModel {
        HomeViewModel(
            getPostsUseCase = GetPostsUseCase(
                blogPostRepository = BlogPostRepositoryImpl(
                    blogPostsApi = get()
                )
            )
        )
    }
}