package com.vitorfg8.models

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val title:String,
    val description:String,
    val timestamp:Long
)
