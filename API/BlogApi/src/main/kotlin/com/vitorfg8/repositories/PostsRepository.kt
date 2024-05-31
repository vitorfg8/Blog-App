package com.vitorfg8.repositories

import com.vitorfg8.models.BlogPost

class PostsRepository {

    val blogPosts get() = _blogPosts.toList()

    fun save(blogPost: BlogPost){
        _blogPosts.add(blogPost)
    }

    companion object{
        private val _blogPosts = mutableListOf<BlogPost>()
    }
}