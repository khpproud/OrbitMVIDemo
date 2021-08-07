package com.example.orbitmvidemopost.app.features.postlist.viewmodel

import android.os.Parcelable
import com.example.orbitmvidemopost.domain.model.PostOverview
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostListState(
    val overviews: List<PostOverview> = emptyList()
) : Parcelable