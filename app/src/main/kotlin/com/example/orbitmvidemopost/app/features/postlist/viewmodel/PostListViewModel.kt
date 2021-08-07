package com.example.orbitmvidemopost.app.features.postlist.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.orbitmvidemopost.app.common.NavigationEvent
import com.example.orbitmvidemopost.domain.model.PostOverview
import com.example.orbitmvidemopost.domain.repository.IPostRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PostListViewModel(
    savedStateHandle: SavedStateHandle,
    private val postRepository: IPostRepository,
    exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.w("PostListViewModel", "caught exception", throwable)
    }
) : ViewModel(), ContainerHost<PostListState, NavigationEvent> {

    override val container = container<PostListState, NavigationEvent>(
        initialState = PostListState(),
        savedStateHandle = savedStateHandle,
        settings = Container.Settings(exceptionHandler = exceptionHandler)
    ) {
        if (it.overviews.isEmpty()) {
            loadOverviews()
        }
    }

    private fun loadOverviews() = intent {
        val overviews = postRepository.getOverviews()

        reduce {
            state.copy(overviews = overviews)
        }
    }

    fun onPostClicked(post: PostOverview) = intent {
        postSideEffect(OpenPostNavigationEvent(post))
    }

    fun onPostLongClicked() = intent {
        throw IllegalStateException("Catch me!")
    }
}