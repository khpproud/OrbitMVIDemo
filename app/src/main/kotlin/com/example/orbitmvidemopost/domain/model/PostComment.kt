package com.example.orbitmvidemopost.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostComment(
    val id: Int,
    val name: String,
    val email: String,
    val body: String
) : Parcelable