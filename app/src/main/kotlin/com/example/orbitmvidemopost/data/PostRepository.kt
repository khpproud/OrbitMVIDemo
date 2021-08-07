package com.example.orbitmvidemopost.data

import com.example.orbitmvidemopost.data.network.AvatarUrlGenerator
import com.example.orbitmvidemopost.data.network.PostNetworkDataSource
import com.example.orbitmvidemopost.domain.Status
import com.example.orbitmvidemopost.domain.model.PostComment
import com.example.orbitmvidemopost.domain.model.PostDetail
import com.example.orbitmvidemopost.domain.model.PostOverview
import com.example.orbitmvidemopost.domain.repository.IPostRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class PostRepository(
    private val networkDataSource: PostNetworkDataSource,
    private val avatarUrlGenerator: AvatarUrlGenerator
) : IPostRepository {
    override suspend fun getOverviews(): List<PostOverview> = coroutineScope {
        val posts = async { networkDataSource.getPosts() }
        val users = async { networkDataSource.getUsers() }

        posts.await().map { post ->
            val user = users.await().first() { it.id == post.userId }

            PostOverview(
                post.id,
                avatarUrlGenerator.generateUrl(user.email),
                post.title,
                user.name
            )
        }
    }

    override suspend fun getDetail(id: Int): Status<PostDetail> = coroutineScope {
        when (val postData = networkDataSource.getPost(id)) {
            is Status.Success -> {
                val comments = async {
                    networkDataSource.getComments()
                        .filter { it.postId == postData.data.id }
                }

                Status.Success(
                    PostDetail(
                        postData.data.id,
                        postData.data.body,
                        comments.await().map {
                            PostComment(
                                it.id,
                                it.name,
                                it.email,
                                it.body
                            )
                        }
                    )
                )
            }
            is Status.Failure -> Status.Failure(postData.exception)
        }
    }
}