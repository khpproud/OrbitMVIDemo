package com.example.orbitmvidemopost.data.network

import com.example.orbitmvidemopost.data.model.CommentData
import com.example.orbitmvidemopost.data.model.PostData
import com.example.orbitmvidemopost.data.model.UserData
import retrofit2.http.GET
import retrofit2.http.Path

// https://jsonplaceholder.typicode.com
interface TypicodeService {
    @GET("posts/{id}")
    suspend fun post(@Path("id") id: Int): PostData

    @GET("posts")
    suspend fun posts(): List<PostData>

    @GET("users/{id}")
    suspend fun user(@Path("id") id: Int): UserData

    @GET("users")
    suspend fun users(): List<UserData>

    @GET("comments")
    suspend fun comments(): List<CommentData>

    @GET("posts/{id}/comments")
    suspend fun comments(@Path("id") postId: Int): List<CommentData>
}