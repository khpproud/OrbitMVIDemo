package com.example.orbitmvidemopost.app.features.postdetails.ui

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.orbitmvidemopost.R
import com.example.orbitmvidemopost.app.common.SeparatorDecoration
import com.example.orbitmvidemopost.app.common.viewBinding
import com.example.orbitmvidemopost.app.features.postdetails.viewmodel.PostDetailState
import com.example.orbitmvidemopost.app.features.postdetails.viewmodel.PostDetailViewModel
import com.example.orbitmvidemopost.databinding.PostDetailsFragmentBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.viewmodel.observe

class PostDetailsFragment : Fragment(R.layout.post_details_fragment) {

    private val args: PostDetailsFragmentArgs by navArgs()
    private val viewModel: PostDetailViewModel by stateViewModel { parametersOf(args.overview) }
    private var initialized: Boolean = false
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    private val binding by viewBinding<PostDetailsFragmentBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPostCommentsList.run {
            layoutManager = LinearLayoutManager(activity)
            ViewCompat.setNestedScrollingEnabled(this, false)

            addItemDecoration(
                SeparatorDecoration(
                    requireActivity(),
                    R.dimen.separator_margin_start,
                    R.dimen.separator_margin_end
                )
            )

            adapter = groupAdapter
        }

        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    private fun render(state: PostDetailState) {
        if (!initialized) {
            initialized = true
            (requireActivity() as AppCompatActivity).supportActionBar?.apply {
                title = state.postOverview.username
                Glide.with(requireContext())
                    .load(state.postOverview.avatarUrl)
                    .apply(RequestOptions.overrideOf(resources.getDimensionPixelSize(R.dimen.toolbar_logo_size)))
                    .apply(RequestOptions.circleCropTransform())
                    .into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            val logo = LayerDrawable(arrayOf(resource)).apply {
                                setLayerInset(0, 0, 0, resources.getDimensionPixelSize(R.dimen.toolbar_logo_padding_end), 0)
                            }

                            setLogo(logo)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            placeholder?.let(::setLogo)
                        }
                    })
            }
        }
        binding.tvPostTitle.text = state.postOverview.title

        if (state is PostDetailState.Details) {
            binding.tvPostBody.text = state.post.body

            val comments = state.post.comments.size
            binding.tvPostCommentCount.text = requireContext().resources.getQuantityString(
                R.plurals.comments,
                comments,
                comments
            )

            groupAdapter.update(state.post.comments.map(::PostCommentItem))
        }
    }
}