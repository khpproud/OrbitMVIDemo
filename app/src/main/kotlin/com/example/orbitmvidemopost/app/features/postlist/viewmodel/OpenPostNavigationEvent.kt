package com.example.orbitmvidemopost.app.features.postlist.viewmodel

import com.example.orbitmvidemopost.app.common.NavigationEvent
import com.example.orbitmvidemopost.domain.model.PostOverview

data class OpenPostNavigationEvent(
    val post: PostOverview
) : NavigationEvent