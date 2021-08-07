package com.example.orbitmvidemopost.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDetail(
    val id: Int,
    val body: String,
    val comments: List<PostComment>
) : Parcelable