package com.vitorfg8.models

import kotlinx.serialization.Serializable

@Serializable
data class BlogPost(
    val title:String,
    val description:String,
    val date:String
)
