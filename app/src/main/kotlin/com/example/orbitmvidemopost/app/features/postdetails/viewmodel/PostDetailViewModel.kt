package com.example.orbitmvidemopost.app.features.postdetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.orbitmvidemopost.domain.Status
import com.example.orbitmvidemopost.domain.model.PostOverview
import com.example.orbitmvidemopost.domain.repository.IPostRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PostDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val postRepository: IPostRepository,
    private val postOverview: PostOverview
) : ViewModel(), ContainerHost<PostDetailState, Nothing> {

    override val container = container<PostDetailState, Nothing>(
        PostDetailState.NoDetailsAvailable(postOverview),
        savedStateHandle
    ) {
        if (it !is PostDetailState.Details) {
            loadDetails()
        }
    }

    private fun loadDetails() = intent {
        val status = postRepository.getDetail(postOverview.id)

        reduce {
            when (status) {
                is Status.Success -> PostDetailState.Details(state.postOverview, status.data)
                is Status.Failure -> PostDetailState.NoDetailsAvailable(state.postOverview)
            }
        }
    }


}