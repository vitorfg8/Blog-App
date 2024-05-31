package com.vitorfg8.plugins

import com.vitorfg8.models.BlogPost
import com.vitorfg8.repositories.PostsRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val repository = PostsRepository()
    routing {
        get("/blogposts") {
            call.respond(repository.blogPosts)
        }
        post("/blogposts"){
            val blogPost = call.receive<BlogPost>()
            repository.save(blogPost)
            call.respondText("Post created successfully", status = HttpStatusCode.Created)
        }
    }
}
