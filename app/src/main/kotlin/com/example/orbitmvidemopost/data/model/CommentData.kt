package com.example.orbitmvidemopost.data.model

data class CommentData(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)