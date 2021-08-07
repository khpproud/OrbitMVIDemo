package com.example.orbitmvidemopost.app.features.postlist.ui

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.orbitmvidemopost.R
import com.example.orbitmvidemopost.app.features.postlist.viewmodel.PostListViewModel
import com.example.orbitmvidemopost.databinding.PostListItemBinding
import com.example.orbitmvidemopost.domain.model.PostOverview
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

data class PostListItem(
    private val post: PostOverview, private val viewModel: PostListViewModel
) : BindableItem<PostListItemBinding>() {

    override fun initializeViewBinding(view: View): PostListItemBinding = PostListItemBinding.bind(view)

    override fun isSameAs(other: Item<*>): Boolean = other is PostListItem && post.id == other.post.id

    override fun hasSameContentAs(other: Item<*>): Boolean = other is PostListItem && post == other.post

    override fun getLayout(): Int = R.layout.post_list_item

    override fun bind(viewBinding: PostListItemBinding, position: Int) {
        Glide.with(viewBinding.root.context).load(post.avatarUrl)
            .apply(RequestOptions.circleCropTransform()).into(viewBinding.ivPostAvatar)

        viewBinding.tvPostTitle.text = post.title
        viewBinding.tvPostUsername.text = post.username

        viewBinding.root.setOnClickListener { viewModel.onPostClicked(post) }

        viewBinding.root.setOnLongClickListener {
            viewModel.onPostLongClicked()
            true
        }
    }




}