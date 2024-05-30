package com.vitorfg8.plugins

import com.vitorfg8.models.Post
import com.vitorfg8.repositories.PostsRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val repository = PostsRepository()
    routing {
        get("/posts") {
            call.respond(repository.posts)
        }
        post("/posts"){
            val post = call.receive<Post>()
            repository.save(post)
            call.respondText("Post created successfully", status = HttpStatusCode.Created)
        }
    }
}
