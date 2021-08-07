package com.example.orbitmvidemopost.app.features.postdetails.viewmodel

import android.os.Parcelable
import com.example.orbitmvidemopost.domain.model.PostDetail
import com.example.orbitmvidemopost.domain.model.PostOverview
import kotlinx.parcelize.Parcelize

sealed class PostDetailState : Parcelable {

    abstract val postOverview: PostOverview

    @Parcelize
    data class Details(override val postOverview: PostOverview, val post: PostDetail) : PostDetailState()

    @Parcelize
    data class NoDetailsAvailable(override val postOverview: PostOverview) : PostDetailState()
}