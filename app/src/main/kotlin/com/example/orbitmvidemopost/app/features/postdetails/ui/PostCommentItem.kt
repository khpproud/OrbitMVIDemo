package com.example.orbitmvidemopost.app.features.postdetails.ui

import android.view.View
import com.example.orbitmvidemopost.R
import com.example.orbitmvidemopost.databinding.PostCommentListItemBinding
import com.example.orbitmvidemopost.domain.model.PostComment
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

data class PostCommentItem(
    private val post: PostComment
) : BindableItem<PostCommentListItemBinding>() {

    override fun initializeViewBinding(view: View) = PostCommentListItemBinding.bind(view)

    override fun isSameAs(other: Item<*>): Boolean = other is PostCommentItem && post.id == other.post.id

    override fun hasSameContentAs(other: Item<*>): Boolean = other is PostCommentItem && post == other.post

    override fun getLayout(): Int = R.layout.post_comment_list_item

    override fun bind(viewBinding: PostCommentListItemBinding, position: Int) {
        viewBinding.run {
            tvCommentUsername.text = post.name
            tvCommentEmail.text = post.email
            tvCommentBody.text = post.body
        }
    }




}