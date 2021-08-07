package com.example.orbitmvidemopost.domain.repository

import com.example.orbitmvidemopost.domain.Status
import com.example.orbitmvidemopost.domain.model.PostDetail
import com.example.orbitmvidemopost.domain.model.PostOverview

interface IPostRepository {
    suspend fun getOverviews(): List<PostOverview>
    suspend fun getDetail(id: Int): Status<PostDetail>
}