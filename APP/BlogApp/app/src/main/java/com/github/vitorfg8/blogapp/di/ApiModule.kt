package com.github.vitorfg8.blogapp.di

import com.github.vitorfg8.blogapp.data.api.BlogPostsApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { provideRetrofit() }
    single { provideBlogPostsApi(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideBlogPostsApi(retrofit: Retrofit): BlogPostsApi {
    return retrofit.create(BlogPostsApi::class.java)
}