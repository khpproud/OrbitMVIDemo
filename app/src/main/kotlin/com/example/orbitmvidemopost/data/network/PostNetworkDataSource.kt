package com.example.orbitmvidemopost.data.network

import com.example.orbitmvidemopost.data.model.CommentData
import com.example.orbitmvidemopost.data.model.PostData
import com.example.orbitmvidemopost.data.model.UserData
import com.example.orbitmvidemopost.domain.Status
import retrofit2.HttpException
import java.io.IOException

class PostNetworkDataSource(private val typicodeService: TypicodeService) {
    suspend fun getPost(id: Int): Status<PostData> {
        return try {
            Status.Success(typicodeService.post(id))
        } catch (e: IOException) {
            Status.Failure(e)
        } catch (e: HttpException) {
            Status.Failure(e)
        }
    }

    suspend fun getPosts(): List<PostData> {
        return try {
            typicodeService.posts().sortedBy { it.title }
        } catch (ignore: IOException) {
            emptyList()
        } catch (ignore: HttpException) {
            emptyList()
        }
    }

    suspend fun getUsers(): List<UserData> {
        return try {
            typicodeService.users()
        } catch (ignore: IOException) {
            emptyList()
        } catch (ignore: HttpException) {
            emptyList()
        }
    }

    suspend fun getUser(id: Int): UserData? {
        return try {
            typicodeService.user(id)
        } catch (ignore: IOException) {
            null
        } catch (ignore: HttpException) {
            null
        }
    }

    suspend fun getComments(): List<CommentData> {
        return try {
            typicodeService.comments()
        } catch (ignore: IOException) {
            emptyList()
        } catch (ignore: HttpException) {
            emptyList()
        }
    }

    suspend fun getComments(postId: Int): List<CommentData> {
        return try {
            typicodeService.comments(postId)
        } catch (ignore: IOException) {
            emptyList()
        } catch (ignore: HttpException) {
            emptyList()
        }
    }
}