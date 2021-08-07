package com.example.orbitmvidemopost.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostOverview(
    val id: Int,
    val avatarUrl: String,
    val title: String,
    val username: String
) : Parcelable