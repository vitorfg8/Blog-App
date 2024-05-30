package com.vitorfg8.repositories

import com.vitorfg8.models.Post

class PostsRepository {

    val posts get() = _posts.toList()

    fun save(post: Post){
        _posts.add(post)
    }

    companion object{
        private val _posts = mutableListOf<Post>()
    }
}